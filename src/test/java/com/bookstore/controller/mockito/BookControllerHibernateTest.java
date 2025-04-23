package com.bookstore.controller.mockito;

import com.bookstore.controller.BookController;
import com.bookstore.controller.ThymeleafUtils;
import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.FileLauncherReporter;
import org.approvaltests.reporters.MultiReporter;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookControllerHibernateTest {

    @Test
    public void testDirectRenderingOfThymeleafTemplate() throws Exception {
        var dataSource = mock(DataSource.class);
        setupDataSource(dataSource);
        var model = new ConcurrentModel();

        String page = new BookController(dataSource).listBooks(model);
        String htmlOutput = ThymeleafUtils.renderPage(page, model);

        Approvals.verifyHtml(htmlOutput,
            new Options().withReporter(new MultiReporter(DiffReporter.INSTANCE, new FileLauncherReporter())));
    }

    public static void setupDataSource(DataSource dataSource) throws SQLException {
        var connection = mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(connection);

        var statement = mock(java.sql.PreparedStatement.class);
        var resultSet = mock(java.sql.ResultSet.class);
        when(connection.prepareStatement(org.mockito.ArgumentMatchers.anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true, false);

        // Book fields
        when(resultSet.getLong("ID")).thenReturn(1L);
        when(resultSet.getString("TITLE")).thenReturn("Twilit");
        when(resultSet.getString("SUBTITLE")).thenReturn("A Vampire Romance Parody");
        when(resultSet.getString("DESCRIPTION")).thenReturn("Girl falls in love with a slightly illuminated vampire");
        when(resultSet.getString("ISBN13")).thenReturn("9783456789012");
        when(resultSet.getString("ISBN10")).thenReturn("0345678901");
        when(resultSet.getString("EDITION")).thenReturn("1st");
        when(resultSet.getInt("PAGE_COUNT")).thenReturn(498);
        when(resultSet.getString("LANGUAGE")).thenReturn("English");
        when(resultSet.getString("COVER_IMAGE_URL")).thenReturn(null);
        when(resultSet.getBigDecimal("PRICE")).thenReturn(new java.math.BigDecimal("12.99"));
        java.sql.Date pubDate = java.sql.Date.valueOf("2008-10-05");
        when(resultSet.getDate("PUBLICATION_DATE")).thenReturn(pubDate);

        // Publisher fields
        when(resultSet.getLong("PUBLISHER_ID")).thenReturn(10L);
        when(resultSet.getString("NAME")).thenReturn("Parody Books");
        when(resultSet.getString("WEBSITE")).thenReturn("https://parodybooks.com");
        when(resultSet.getString("EMAIL")).thenReturn("info@parodybooks.com");
        when(resultSet.getString("PHONE")).thenReturn("+1-555-1234");

        // Address fields
        when(resultSet.getLong("ADDRESS_ID")).thenReturn(100L);
        when(resultSet.getString("STREET_LINE1")).thenReturn("123 Twilight Ave");
        when(resultSet.getString("STREET_LINE2")).thenReturn(null);

        // City fields
        when(resultSet.getLong("CITY_ID")).thenReturn(1000L);
        when(resultSet.getString("city_name")).thenReturn("Forks");
        when(resultSet.getString("POSTAL_CODE")).thenReturn("12345");

        // State fields
        when(resultSet.getLong("STATE_ID")).thenReturn(10000L);
        when(resultSet.getString("state_name")).thenReturn("Washington");
        when(resultSet.getString("state_code")).thenReturn("WA");

        // Country fields
        when(resultSet.getLong("COUNTRY_ID")).thenReturn(100000L);
        when(resultSet.getString("country_name")).thenReturn("United States");
        when(resultSet.getString("country_code")).thenReturn("US");

        // wasNull logic for IDs (none are null)
        when(resultSet.wasNull()).thenReturn(false);
    }

}
