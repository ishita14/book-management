package com.bookmanagement.service;

import com.bookmanagement.dto.BookDto;
import com.bookmanagement.entity.Book;
import com.bookmanagement.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setPublishedYear(bookDto.getPublishedYear());
        return bookRepository.save(book);
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public Book updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));
        
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setPublishedYear(bookDto.getPublishedYear());
        
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
} 