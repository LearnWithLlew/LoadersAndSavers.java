package com.bookstore.model;

import jakarta.persistence.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Entity
public class Country {

    public Country() {
    }

    public Country(ResultSet rs) throws SQLException {
        this.id = rs.getLong("COUNTRY_ID");
        this.name = rs.getString("country_name");
        this.code = rs.getString("country_code");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @OneToMany(mappedBy = "country")
    private Set<State> states;

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

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }
}
