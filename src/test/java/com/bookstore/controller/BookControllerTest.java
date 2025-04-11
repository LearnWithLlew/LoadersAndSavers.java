package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.FileLauncherReporter;
import org.approvaltests.reporters.MultiReporter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.ConcurrentModel;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BookControllerTest {

    @MockBean
    private BookService bookService;

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
