package com.bookmanagement.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private String genre;
    private Integer publishedYear;
} 