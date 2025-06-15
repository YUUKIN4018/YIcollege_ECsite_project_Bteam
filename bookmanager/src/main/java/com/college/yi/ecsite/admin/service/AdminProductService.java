package com.college.yi.ecsite.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.college.yi.ecsite.admin.dto.AdminProductListDto;
import com.college.yi.ecsite.admin.repositoty.CategoryMapper;
import com.college.yi.ecsite.admin.repositoty.ProductMapper;
import com.college.yi.ecsite.entity.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    // 一覧取得機能
    public List<AdminProductListDto> getProductList(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        List<Product> products = productMapper.findAllNotDeleted(pageSize, offset);

        return products.stream().map(product -> {
            AdminProductListDto dto = new AdminProductListDto();
            dto.setProductId(product.getProductId());
            
            AdminProductListDto mainImage = null;
            dto.setImageUrl(mainImage != null ? mainImage.getImageUrl() : null);

            dto.setPrice(product.getPrice().intValue());

            // categoryId から parentCategoryId を取得
            Long parentCategoryId = categoryMapper.findParentCategoryId(product.getCategoryId());
            dto.setParentCategoryId(parentCategoryId);
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setCreatedAt(product.getCreatedAt());
            dto.setUpdatedAt(product.getUpdatedAt());
            return dto;
        }).collect(Collectors.toList());
    }
}
