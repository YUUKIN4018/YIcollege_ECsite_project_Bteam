package com.college.yi.ecsite.admin.repositoty;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.college.yi.ecsite.entity.ProductImage;

@Mapper
public interface ProductImageMapper {
    @Select("SELECT * FROM product_images WHERE product_id = #{productId} AND is_main = true LIMIT 1")
    ProductImage findMainImageByProductId(Long productId);

    @Insert("""
                INSERT INTO product_images (product_id, image_url, sort_order, is_main)
                VALUES (#{productId}, #{imageUrl}, 1, true)
            """)
    void insert(ProductImage image);
}
