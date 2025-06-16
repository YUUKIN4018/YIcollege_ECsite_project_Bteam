package com.college.yi.ecsite.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Category {
    private Long categoryId;
    private Long parentCategoryId;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

