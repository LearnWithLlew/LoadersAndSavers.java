package com.bookstore.controller;

import com.bookstore.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookUtils {

    public static Book getTwilit() {
        // Create country
        Country country = new Country();
        country.setName("United States");

        // Create state
        State state = new State();
        state.setCountry(country);

        // Create city
        City city = new City();
        city.setState(state);

        // Create address
        Address address = new Address();
        address.setCity(city);

        // Create publisher
        Publisher publisher = new Publisher();
        publisher.setAddress(address);

        // Create book
        Book book = new Book();
        book.setTitle("Twilit");
        book.setSubtitle("A Vampire Romance Parody");
        book.setIsbn13("9783456789012");
        book.setPublisher(publisher);
        book.setPublicationDate(LocalDate.of(2008, 10, 5));
        book.setPageCount(498);
        book.setLanguage("English");
        book.setDescription("Girl falls in love with a slightly illuminated vampire");
        book.setPrice(new BigDecimal("12.99"));

        return book;
    }
}