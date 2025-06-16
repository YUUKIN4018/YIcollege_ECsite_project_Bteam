package com.college.yi.ecsite.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Product {
    private Long productId;
    private Long shopId;
    private Long categoryId;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal taxRate;
    private Integer stockQuantity;
    private Short status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
