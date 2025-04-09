package com.bookstore.controller;

import com.bookstore.model.*;
import com.bookstore.service.BookService;
import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.FileLauncherReporter;
import org.approvaltests.reporters.MultiReporter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book getBook() {
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

    @Test
    public void testListBooks() throws Exception {
        List<Book> books = List.of(getBook());
        when(bookService.getTop10Books()).thenReturn(books);

        MvcResult result = mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andReturn();

        String htmlOutput = result.getResponse().getContentAsString();
        Approvals.verifyHtml(htmlOutput,
            new Options().withReporter(new MultiReporter(DiffReporter.INSTANCE, new FileLauncherReporter())));
    }
}
