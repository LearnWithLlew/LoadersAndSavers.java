package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.HibernateBookService;
import com.spun.util.persistence.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {

    private final HibernateBookService hibernateBookService;
    
    @Autowired
    public BookController(HibernateBookService hibernateBookService) {
        this.hibernateBookService = hibernateBookService;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        model.addAttribute("books", hibernateBookService.getTop10Books());
        return "index";
    }

    @GetMapping("/V2")
    public String listBooksV2(Model model) {
        return listBooks(model, hibernateBookService::getTop10Books);
    }

    public static String listBooks(Model model, Loader<List<Book>> loader) {
        model.addAttribute("books", loader.load());
        return "index";
    }
}
