package com.bookstore.controller.mockito;

import com.bookstore.controller.BookController;
import com.bookstore.controller.BookUtils;
import com.bookstore.controller.ThymeleafUtils;
import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.FileLauncherReporter;
import org.approvaltests.reporters.MultiReporter;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookControllerTest {

    @Test
    public void testDirectRenderingOfThymeleafTemplate() throws Exception {
        BookService bookService = mock(BookService.class);
        
        List<Book> books = List.of(BookUtils.getTwilit());
        when(bookService.getTop10Books()).thenReturn(books);
        var model = new ConcurrentModel();

        String page = new BookController(bookService).listBooks(model);
        String htmlOutput = ThymeleafUtils.renderPage(page, model);

        Approvals.verifyHtml(htmlOutput,
            new Options().withReporter(new MultiReporter(DiffReporter.INSTANCE, new FileLauncherReporter())));
    }

}
