package com.bookstore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String website;
    private String email;
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    
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
    
    public String getWebsite() {
        return website;
    }
    
    public void setWebsite(String website) {
        assert website != null : "website cannot be null";
        this.website = website;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        assert email != null : "email cannot be null";
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        assert phone != null : "phone cannot be null";
        this.phone = phone;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        assert address != null : "address cannot be null";
        this.address = address;
    }
}
