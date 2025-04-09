package com.bookstore.controller;

import com.bookstore.model.Book;
import com.spun.util.persistence.Loader;
import org.approvaltests.Approvals;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.FileLauncherReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;
import org.lambda.functions.Function2;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

@UseReporter({DiffReporter.class, FileLauncherReporter.class})
public class BookControllerDirectTest {

    @Test
    public void testDirectRenderingOfThymeleafTemplateWithLoader() throws Exception {
        var model = new ConcurrentModel();
        String page = BookController.listBooks(model, () -> List.of(BookUtils.getTwilit()));
        String htmlOutput = ThymeleafUtils.renderPage(page, model);
        Approvals.verifyHtml(htmlOutput);
    }

    @Test
    public void testDirectRendering() throws Exception {
        Function2<Model, Loader<List<Book>>, String> stuff = BookController::listBooks;
        verifyHtml(stuff, () -> List.of(BookUtils.getTwilit()));
    }

    private <P1> void verifyHtml(Function2<Model, P1, String> call, P1 p1) {
        var model = new ConcurrentModel();
        String page = call.call(model, p1);
        String htmlOutput = ThymeleafUtils.renderPage(page, model);
        Approvals.verifyHtml(htmlOutput);
    }
}