package com.bookstore.controller;

import com.bookstore.model.*;
import com.spun.util.persistence.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    public final DataSource dataSource;

    @Autowired
    public BookController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> books1 = new ArrayList<>();
        Map<Long, Book> bookMap = new HashMap<>();
        Map<Long, Publisher> publisherMap = new HashMap<>();
        Map<Long, Address> addressMap = new HashMap<>();
        Map<Long, City> cityMap = new HashMap<>();
        Map<Long, State> stateMap = new HashMap<>();
        Map<Long, Country> countryMap = new HashMap<>();

        String sql = "SELECT b.*, p.*, a.*, c.id as city_id, c.name as city_name, c.postal_code, " +
                "s.id as state_id, s.name as state_name, s.code as state_code, " +
                "co.id as country_id, co.name as country_name, co.code as country_code " +
                "FROM Books b " +
                "LEFT JOIN Publishers p ON p.id = b.publisher_id " +
                "LEFT JOIN Addresses a ON a.id = p.address_id " +
                "LEFT JOIN Cities c ON c.id = a.city_id " +
                "LEFT JOIN States s ON s.id = c.state_id " +
                "LEFT JOIN Countries co ON co.id = s.country_id " +
                "ORDER BY b.id FETCH FIRST 10 ROWS ONLY";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                try {
                    // Process Book
                    Long bookId = rs.getLong("ID");
                    Book book = bookMap.get(bookId);
                    if (book == null) {
                        book = new Book();
                        book.setId(bookId);
                        book.setTitle(rs.getString("TITLE"));
                        book.setSubtitle(rs.getString("SUBTITLE"));
                        book.setIsbn13(rs.getString("ISBN13"));
                        book.setIsbn10(rs.getString("ISBN10"));
                        book.setEdition(rs.getString("EDITION"));
                        book.setPageCount(rs.getInt("PAGE_COUNT"));
                        book.setLanguage(rs.getString("LANGUAGE"));
                        book.setDescription(rs.getString("DESCRIPTION"));
                        book.setCoverImageUrl(rs.getString("COVER_IMAGE_URL"));
                        book.setPrice(rs.getBigDecimal("PRICE"));

                        java.sql.Date pubDate = rs.getDate("PUBLICATION_DATE");
                        if (pubDate != null) {
                            book.setPublicationDate(pubDate.toLocalDate());
                        }

                        // Create default objects to avoid null pointer exceptions in the template
                        Publisher publisher = new Publisher();
                        Address address = new Address();
                        City city = new City();
                        State state = new State();

                        // Set up the object graph with default objects
                        city.setState(state);
                        address.setCity(city);
                        publisher.setAddress(address);
                        book.setPublisher(publisher);

                        bookMap.put(bookId, book);
                        books1.add(book);
                    }

                    // Process Publisher
                    Long publisherId = rs.getLong("PUBLISHER_ID");
                    if (!rs.wasNull()) {
                        Publisher publisher = publisherMap.get(publisherId);
                        if (publisher == null) {
                            publisher = new Publisher();
                            publisher.setId(publisherId);
                            publisher.setName(rs.getString("NAME"));
                            publisher.setWebsite(rs.getString("WEBSITE"));
                            publisher.setEmail(rs.getString("EMAIL"));
                            publisher.setPhone(rs.getString("PHONE"));

                            // Create default objects to avoid null pointer exceptions
                            Address address = new Address();
                            City city = new City();
                            State state = new State();

                            // Set up the object graph with default objects
                            city.setState(state);
                            address.setCity(city);
                            publisher.setAddress(address);

                            publisherMap.put(publisherId, publisher);
                        }
                        book.setPublisher(publisher);

                        // Process Address
                        Long addressId = rs.getLong("ADDRESS_ID");
                        if (!rs.wasNull()) {
                            Address address = addressMap.get(addressId);
                            if (address == null) {
                                address = new Address();
                                address.setId(addressId);
                                address.setStreetLine1(rs.getString("STREET_LINE1"));
                                address.setStreetLine2(rs.getString("STREET_LINE2"));

                                // Create default objects to avoid null pointer exceptions
                                City city = new City();
                                State state = new State();

                                // Set up the object graph with default objects
                                city.setState(state);
                                address.setCity(city);

                                addressMap.put(addressId, address);
                            }
                            publisher.setAddress(address);

                            // Process City
                            Long cityId = rs.getLong("CITY_ID");
                            if (!rs.wasNull()) {
                                City city = cityMap.get(cityId);
                                if (city == null) {
                                    city = new City();
                                    city.setId(cityId);
                                    city.setName(rs.getString("city_name"));
                                    city.setPostalCode(rs.getString("POSTAL_CODE"));

                                    // Create default objects to avoid null pointer exceptions
                                    State state = new State();

                                    // Set up the object graph with default objects
                                    city.setState(state);

                                    cityMap.put(cityId, city);
                                }
                                address.setCity(city);

                                // Process State
                                Long stateId = rs.getLong("STATE_ID");
                                if (!rs.wasNull()) {
                                    State state = stateMap.get(stateId);
                                    if (state == null) {
                                        state = new State();
                                        state.setId(stateId);
                                        state.setName(rs.getString("state_name"));
                                        state.setCode(rs.getString("state_code"));
                                        stateMap.put(stateId, state);
                                    }
                                    city.setState(state);

                                    // Process Country
                                    Country country = new Country(rs);
                                    state.setCountry(country);
                                    countryMap.put(country.getId(), country);
                                }
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Book> books = books1;
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/V2")
    public String listBooksV2(Model model) {
        return listBooks(model, null);
    }

    public static String listBooks(Model model, Loader<List<Book>> loader) {
        model.addAttribute("books", loader.load());
        return "index";
    }
}
