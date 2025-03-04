package com.bookmanagement.dto;

import lombok.Data;
import java.util.List;

@Data
public class BorrowRequestDto {
    private List<Long> bookIds;
} 