package com.bookstore.service;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Object[]> publisherCountries = bookRepository.findBooksWithPublisherCountry();
        
        // Create a map of book ID to country name
        Map<Long, String> bookCountryMap = new HashMap<>();
        for (Object[] result : publisherCountries) {
            Long bookId = ((Number) result[0]).longValue();
            String countryName = (String) result[1];
            bookCountryMap.put(bookId, countryName);
        }
        
        // Add country information to each book
        for (Book book : books) {
            String publisherCountry = bookCountryMap.getOrDefault(book.getId(), "Unknown");
            
            if (book.getDescription() != null) {
                book.setDescription(book.getDescription() + " [" + publisherCountry + "]");
            } else {
                book.setDescription("[" + publisherCountry + "]");
            }
        }
        
        return books;
    }
}
