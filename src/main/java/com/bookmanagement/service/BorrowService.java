package com.bookmanagement.service;

import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.BorrowRecord;
import com.bookmanagement.entity.User;
import com.bookmanagement.repository.BookRepository;
import com.bookmanagement.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BorrowService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;

    public BorrowService(BorrowRecordRepository borrowRecordRepository, BookRepository bookRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void borrowBooks(User user, List<Long> bookIds) {
        for (Long bookId : bookIds) {
            Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found: " + bookId));

            if (!book.isAvailable()) {
                throw new RuntimeException("Book is not available: " + bookId);
            }

            book.setAvailable(false);
            bookRepository.save(book);

            BorrowRecord borrowRecord = new BorrowRecord();
            borrowRecord.setUser(user);
            borrowRecord.setBook(book);
            borrowRecord.setBorrowDate(LocalDateTime.now());
            borrowRecordRepository.save(borrowRecord);
        }
    }

    @Transactional
    public void returnBook(User user, Long bookId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findByUserIdAndBookIdAndIsReturnedFalse(user.getId(), bookId)
            .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        Book book = borrowRecord.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        borrowRecord.setReturned(true);
        borrowRecord.setReturnDate(LocalDateTime.now());
        borrowRecordRepository.save(borrowRecord);
    }

    public List<BorrowRecord> getUserBorrowedBooks(Long userId) {
        return borrowRecordRepository.findByUserIdAndIsReturnedFalse(userId);
    }
} 