package com.bookmanagement.repository;

import com.bookmanagement.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByUserIdAndIsReturnedFalse(Long userId);
    
    @Query("SELECT br.book.id, COUNT(br) as borrowCount FROM BorrowRecord br GROUP BY br.book.id ORDER BY borrowCount DESC")
    List<Object[]> findMostFrequentlyBorrowedBooks();
} 