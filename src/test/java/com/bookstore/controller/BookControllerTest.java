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
import org.springframework.ui.ConcurrentModel;

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

    @Test
    public void testListBooks() throws Exception {
        List<Book> books = List.of(BookUtils.getTwilit());
        when(bookService.getTop10Books()).thenReturn(books);

        MvcResult result = mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andReturn();

        String htmlOutput = result.getResponse().getContentAsString();
        Approvals.verifyHtml(htmlOutput,
            new Options().withReporter(new MultiReporter(DiffReporter.INSTANCE, new FileLauncherReporter())));
    }

    @Test
    public void testDirectRenderingOfThymeleafTemplate() throws Exception {
        List<Book> books = List.of(BookUtils.getTwilit());
        when(bookService.getTop10Books()).thenReturn(books);
        var model = new ConcurrentModel();

        String page = new BookController(bookService).listBooks(model);
        String htmlOutput = ThymeleafUtils.renderPage(page, model);

        Approvals.verifyHtml(htmlOutput,
            new Options().withReporter(new MultiReporter(DiffReporter.INSTANCE, new FileLauncherReporter())));
    }

}
