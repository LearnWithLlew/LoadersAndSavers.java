package com.bookstore.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String streetLine1;
    private String streetLine2;
    
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    
    @OneToMany(mappedBy = "address")
    private Set<Publisher> publishers;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        assert id != null : "id cannot be null";
        this.id = id;
    }
    
    public String getStreetLine1() {
        return streetLine1;
    }
    
    public void setStreetLine1(String streetLine1) {
        assert streetLine1 != null : "streetLine1 cannot be null";
        this.streetLine1 = streetLine1;
    }
    
    public String getStreetLine2() {
        return streetLine2;
    }
    
    public void setStreetLine2(String streetLine2) {
        this.streetLine2 = streetLine2;
    }
    
    public City getCity() {
        return city;
    }
    
    public void setCity(City city) {
        assert city != null : "city cannot be null";
        this.city = city;
    }
    
    public Set<Publisher> getPublishers() {
        return publishers;
    }
    
    public void setPublishers(Set<Publisher> publishers) {
        assert publishers != null : "publishers cannot be null";
        this.publishers = publishers;
    }
}
