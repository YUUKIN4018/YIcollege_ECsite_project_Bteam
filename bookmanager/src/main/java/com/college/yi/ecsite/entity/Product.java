package com.college.yi.ecsite.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Product {
    private Long productId;
    private Long shopId;
    private Long categoryId;
    private String name;
    private String description;
    private int price;
    private double taxRate;
    private int stockQuantity;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}