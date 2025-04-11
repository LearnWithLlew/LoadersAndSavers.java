package com.bookstore.util;

import com.bookstore.model.Address;
import com.bookstore.model.Book;
import com.bookstore.model.City;
import com.bookstore.model.Country;
import com.bookstore.model.Publisher;
import com.bookstore.model.State;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                
                Properties settings = new Properties();
                settings.put(Environment.JAKARTA_JDBC_DRIVER, "org.apache.derby.jdbc.EmbeddedDriver");
                settings.put(Environment.JAKARTA_JDBC_URL, "jdbc:derby:memory:bookstore;create=true");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.DerbyDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.GLOBALLY_QUOTED_IDENTIFIERS, "true");
                settings.put("hibernate.globally_quoted_identifiers_skip_column_definitions", "true");
                
                configuration.setProperties(settings);
                
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(Publisher.class);
                configuration.addAnnotatedClass(Address.class);
                configuration.addAnnotatedClass(City.class);
                configuration.addAnnotatedClass(State.class);
                configuration.addAnnotatedClass(Country.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
