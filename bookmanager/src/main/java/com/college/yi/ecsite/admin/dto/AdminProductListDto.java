package com.college.yi.ecsite.admin.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AdminProductListDto {
    private Long productId;
    private Long parentCategoryId;
    private String name;
    private String imageUrl;
    private String description;
    private Integer price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
