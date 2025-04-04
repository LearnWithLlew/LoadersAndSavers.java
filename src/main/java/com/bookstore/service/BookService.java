package com.bookstore.service;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public List<Book> getTop10Books() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Book> books = bookRepository.findTop10Books(pageable);
        
        for (Book book : books) {
            String publisherCountry = book.getPublisher().getAddress().getCity().getState().getCountry().getName();
            book.setDescription(book.getDescription() + " [" + publisherCountry + "]");
        }
        
        return books;
    }
}
