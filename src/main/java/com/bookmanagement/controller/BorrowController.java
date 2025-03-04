package com.bookmanagement.controller;

import com.bookmanagement.dto.BorrowRequestDto;
import com.bookmanagement.entity.BorrowRecord;
import com.bookmanagement.entity.User;
import com.bookmanagement.service.BorrowService;
import com.bookmanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {
    private final BorrowService borrowService;
    private final UserService userService;

    public BorrowController(BorrowService borrowService, UserService userService) {
        this.borrowService = borrowService;
        this.userService = userService;
    }

    @PostMapping("/books")
    public ResponseEntity<?> borrowBooks(
            Authentication authentication,
            @RequestBody BorrowRequestDto borrowRequest) {
        try {
            User user = userService.findByUsername(authentication.getName());
            borrowService.borrowBooks(user, borrowRequest.getBookIds());
            return ResponseEntity.ok().body("Books borrowed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<?> returnBook(
            Authentication authentication,
            @PathVariable Long bookId) {
        try {
            User user = userService.findByUsername(authentication.getName());
            borrowService.returnBook(user, bookId);
            return ResponseEntity.ok().body("Book returned successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/my-books")
    public ResponseEntity<List<BorrowRecord>> getMyBorrowedBooks(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        return ResponseEntity.ok(borrowService.getUserBorrowedBooks(user.getId()));
    }
} 