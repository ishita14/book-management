package com.bookmanagement.controller;

import com.bookmanagement.dto.BookDto;
import com.bookmanagement.entity.Book;
import com.bookmanagement.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.addBook(bookDto));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        if (title != null) {
            return ResponseEntity.ok(bookService.searchByTitle(title));
        } else if (author != null) {
            return ResponseEntity.ok(bookService.searchByAuthor(author));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        try {
            return ResponseEntity.ok(bookService.updateBook(id, bookDto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 