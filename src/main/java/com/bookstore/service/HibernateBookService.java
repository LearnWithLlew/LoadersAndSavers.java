package com.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class HibernateBookService {

    private final DataSource dataSource;

    @Autowired
    public HibernateBookService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
