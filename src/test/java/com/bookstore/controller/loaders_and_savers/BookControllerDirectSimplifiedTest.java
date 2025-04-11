package com.bookstore.controller.loaders_and_savers;

import com.bookstore.controller.BookController;
import com.bookstore.controller.BookUtils;
import com.bookstore.controller.ThymeleafUtils;
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
public class BookControllerDirectSimplifiedTest {

    @Test
    public void testDirectRendering() throws Exception {
        verifyRenderedHtml(m ->
            BookController.listBooks(
                m,
                () -> List.of(BookUtils.getTwilit())
            ));
    }

    private void verifyRenderedHtml(Function1<Model, String> call) {
        var model = new ConcurrentModel();
        String page = call.call(model);
        String htmlOutput = ThymeleafUtils.renderPage(page, model);
        Approvals.verifyHtml(htmlOutput);
    }
}