package com.college.yi.ecsite.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductImage {
    private Long imageId;
    private Long productId;
    private String imageUrl;
    private Integer sortOrder;
    private Boolean isMain;
    private LocalDateTime createdAt;
}
