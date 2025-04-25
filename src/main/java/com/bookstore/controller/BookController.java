package com.bookstore.controller;

import com.bookstore.service.GeoIpService;

import com.bookstore.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    public final DataSource dataSource;
    private final GeoIpService geoIpService;

    @Autowired
    public BookController(DataSource dataSource, GeoIpService geoIpService) {
        this.dataSource = dataSource;
        this.geoIpService = geoIpService;
    }

    @GetMapping("/")
    public String listBooks(Model model, HttpServletRequest request) {
        // Record page view
        savePageView(request);
        
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
    
    private void savePageView(HttpServletRequest request) {
        String ipAddress = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        String referrer = request.getHeader("Referer");
        String pageUrl = request.getRequestURL().toString();
        String sessionId = request.getSession().getId();
        
        // Get country from IP address (simplified version - in production, use a geolocation service)
        Country country = getCountryFromIp(ipAddress);
        
        // Save page view to database
        String sql = "INSERT INTO PageViews (ip_address, country_id, visit_timestamp, user_agent, session_id, referrer_url, page_url) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, ipAddress);
            ps.setLong(2, country != null ? country.getId() : null);
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(4, userAgent);
            ps.setString(5, sessionId);
            ps.setString(6, referrer);
            ps.setString(7, pageUrl);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // If multiple IP addresses are found (comma-separated), take the first one
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }
        return ipAddress;
    }

    // MaxMind GeoIP2 dependency (add to pom.xml):
    // <dependency>
    //   <groupId>com.maxmind.geoip2</groupId>
    //   <artifactId>geoip2</artifactId>
    //   <version>4.0.0</version>
    // </dependency>

    private Country getCountryFromIp(String ipAddress) {
        // Use GeoIpService to get country name from IP
        String countryName = geoIpService.getCountryName(ipAddress);
        if (countryName == null) {
            Country defaultCountry = new Country();
            defaultCountry.setId(1L);
            defaultCountry.setName("Unknown");
            defaultCountry.setCode("??");
            return defaultCountry;
        }
        // Query your Countries table by name
        String sql = "SELECT * FROM Countries WHERE lower(country_name) = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, countryName.toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Country(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Country defaultCountry = new Country();
        defaultCountry.setId(1L);
        defaultCountry.setName("Unknown");
        defaultCountry.setCode("??");
        return defaultCountry;
    }
}
