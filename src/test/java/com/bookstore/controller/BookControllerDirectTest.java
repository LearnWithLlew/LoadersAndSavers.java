package com.bookstore.controller;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.FileLauncherReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;
import org.lambda.functions.Function1;
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
        verifyHtml(m -> BookController.listBooks(m, () -> List.of(BookUtils.getTwilit())));
    }

    private void verifyHtml(Function1<Model, String> call) {
        var model = new ConcurrentModel();
        String page = call.call(model);
        String htmlOutput = ThymeleafUtils.renderPage(page, model);
        Approvals.verifyHtml(htmlOutput);
    }
}