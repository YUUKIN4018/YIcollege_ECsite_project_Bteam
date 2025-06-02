package com.college.yi.ecsite.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CartItem {
    private Long cartItemId;
    private Long userId;
    private Long productId;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}