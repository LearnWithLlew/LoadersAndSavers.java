package com.bookstore.controller;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.FileLauncherReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;

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
}