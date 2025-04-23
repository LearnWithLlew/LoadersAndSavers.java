package com.bookstore.controller.spring;

import com.bookstore.controller.BookController;
import com.bookstore.controller.mockito.BookControllerHibernateTest;
import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.FileLauncherReporter;
import org.approvaltests.reporters.MultiReporter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerWebServerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataSource dataSource;

    @Test
    public void testListBooks() throws Exception {
        BookControllerHibernateTest.setupDataSource(dataSource);

        MvcResult result = mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andReturn();

        String htmlOutput = result.getResponse().getContentAsString();
        Approvals.verifyHtml(htmlOutput,
            new Options().withReporter(new MultiReporter(DiffReporter.INSTANCE, new FileLauncherReporter())));
    }
}
