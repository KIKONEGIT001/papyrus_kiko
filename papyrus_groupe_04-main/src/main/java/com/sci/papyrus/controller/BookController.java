package com.sci.papyrus.controller;

import com.sci.papyrus.dto.BookDTO;
import com.sci.papyrus.entity.Book;
import com.sci.papyrus.service.BookService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO form) {
        return bookService.create(form);
    }

    @GetMapping
    public List<BookDTO> listBooks() {
        return bookService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    
}
