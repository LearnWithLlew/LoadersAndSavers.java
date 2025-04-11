package com.bookstore.controller.mockito;

import com.bookstore.controller.BookController;
import com.bookstore.controller.ThymeleafUtils;
import com.bookstore.service.HibernateBookService;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.FileLauncherReporter;
import org.approvaltests.reporters.MultiReporter;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.ui.ConcurrentModel;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;

public class BookControllerHibernateTest {

    @Test
    public void testDirectRenderingOfThymeleafTemplate() throws Exception {
        HibernateBookService hibernateBookService = new HibernateBookService(getDataSource());

        var model = new ConcurrentModel();

        String page = new BookController(null, hibernateBookService).listBooksV3(model);
        String htmlOutput = ThymeleafUtils.renderPage(page, model);

        Approvals.verifyHtml(htmlOutput,
            new Options().withReporter(new MultiReporter(DiffReporter.INSTANCE, new FileLauncherReporter())));
    }

    public DataSource getDataSource() throws SQLException {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        ds.setDatabaseName("memory:bookstore;create=true");
        ds.setUser("");
        ds.setPassword("");

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(true);
        populator.addScript(new ClassPathResource("schema.sql"));
        populator.addScript(new ClassPathResource("populate_database.sql"));
        populator.execute(ds);

        return ds;
    }

}
