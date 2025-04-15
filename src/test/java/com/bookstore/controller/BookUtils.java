package com.bookstore.controller;

import com.bookstore.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookUtils {

    public static Book getTwilit() {
        Book book = new Book();
        book.setTitle("Twilit");
        book.setSubtitle("A Vampire Romance Parody");
        book.setIsbn13("9783456789012");
        book.setPublisher(
            new Publisher().setAddress(
                new Address().setCity(
                    new City().setState(
                        new State().setCountry(
                            new Country().setName("United States"))))));
        book.setPublicationDate(LocalDate.of(2008, 10, 5));
        book.setPageCount(498);
        book.setLanguage("English");
        book.setDescription("Girl falls in love with a slightly illuminated vampire");
        book.setPrice(new BigDecimal("12.99"));

        return book;
    }
}