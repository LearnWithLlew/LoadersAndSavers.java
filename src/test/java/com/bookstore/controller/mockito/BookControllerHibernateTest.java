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
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        var dataSource = mock(DataSource.class);
        var connection = mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(connection);

        var statement = mock(java.sql.PreparedStatement.class);
        var resultSet = mock(java.sql.ResultSet.class);
        when(connection.prepareStatement(org.mockito.ArgumentMatchers.anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getLong(org.mockito.ArgumentMatchers.anyInt())).thenReturn(1L);
        when(resultSet.getString(org.mockito.ArgumentMatchers.anyInt())).thenReturn("Title");
        when(resultSet.getInt(org.mockito.ArgumentMatchers.anyInt())).thenReturn(2024);

        return dataSource;
    }

}
