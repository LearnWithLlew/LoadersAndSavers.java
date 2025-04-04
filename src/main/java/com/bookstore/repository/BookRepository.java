package com.bookstore.repository;

import com.bookstore.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.publisher p LEFT JOIN FETCH p.address a LEFT JOIN FETCH a.city c LEFT JOIN FETCH c.state s LEFT JOIN FETCH s.country ORDER BY b.id")
    List<Book> findTop10Books(Pageable pageable);

}
