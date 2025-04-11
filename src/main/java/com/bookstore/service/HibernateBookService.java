package com.bookstore.service;

import com.bookstore.model.Book;
import com.bookstore.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HibernateBookService {

    public List<Book> getTop10Books() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            
            Query<Book> query = session.createQuery(
                "SELECT b FROM Book b LEFT JOIN FETCH b.publisher p " +
                "LEFT JOIN FETCH p.address a " +
                "LEFT JOIN FETCH a.city c " +
                "LEFT JOIN FETCH c.state s " +
                "LEFT JOIN FETCH s.country " +
                "ORDER BY b.id", 
                Book.class
            );
            query.setMaxResults(10);
            
            List<Book> books = query.list();
            session.getTransaction().commit();
            return books;
        }
    }
}
