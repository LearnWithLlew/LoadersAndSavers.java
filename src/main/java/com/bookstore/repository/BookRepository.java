package com.bookstore.repository;

import com.bookstore.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("SELECT b FROM Book b ORDER BY b.id")
    List<Book> findTop10Books(Pageable pageable);
    
    @Query(value = "SELECT b.id as book_id, c.name as country_name FROM Books b " +
           "JOIN Publishers p ON b.publisher_id = p.id " +
           "JOIN Addresses a ON p.address_id = a.id " +
           "JOIN Cities ci ON a.city_id = ci.id " +
           "JOIN States s ON ci.state_id = s.id " +
           "JOIN Countries c ON s.country_id = c.id " +
           "ORDER BY b.id", nativeQuery = true)
    List<Object[]> findBooksWithPublisherCountry();
}
