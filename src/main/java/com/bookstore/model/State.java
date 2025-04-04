package com.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public Country getCountry() {
        return country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    
    public Set<City> getCities() {
        return cities;
    }
    
    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
}
