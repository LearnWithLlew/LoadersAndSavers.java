package com.bookstore.controller.loaders_and_savers;

import com.bookstore.controller.BookController;
import com.bookstore.controller.BookUtils;
import com.bookstore.controller.ThymeleafUtils;
import com.spun.util.persistence.Loader;
import com.spun.util.persistence.Saver;
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
//        var model = new ConcurrentModel();
//        String page = BookController.listBooks(model, () -> List.of(BookUtils.getTwilit()));
//        String htmlOutput = ThymeleafUtils.renderPage(page, model);
//        Approvals.verifyHtml(htmlOutput);
    }
}