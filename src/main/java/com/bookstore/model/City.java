package com.bookstore.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Cities", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "state_id", "postal_code"})
})
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String postalCode;
    
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
    
    @OneToMany(mappedBy = "city")
    private Set<Address> addresses;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public State getState() {
        return state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public Set<Address> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
