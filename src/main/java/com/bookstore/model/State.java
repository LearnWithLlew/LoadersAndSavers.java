package com.bookstore.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "States", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "country_id"})
})
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String code;
    
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
    
    @OneToMany(mappedBy = "state")
    private Set<City> cities;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        assert id != null : "id cannot be null";
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        assert name != null : "name cannot be null";
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        assert code != null : "code cannot be null";
        this.code = code;
    }
    
    public Country getCountry() {
        return country;
    }
    
    public void setCountry(Country country) {
        assert country != null : "country cannot be null";
        this.country = country;
    }
    
    public Set<City> getCities() {
        return cities;
    }
    
    public void setCities(Set<City> cities) {
        assert cities != null : "cities cannot be null";
        this.cities = cities;
    }
}
