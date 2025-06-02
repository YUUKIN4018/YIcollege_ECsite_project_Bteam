package com.college.yi.ecsite.front.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.college.yi.ecsite.entity.Product;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM products WHERE product_id = #{productId}")
    Product findById(Long productId);
    
    @Select({
        "<script>",
        "SELECT * FROM products WHERE product_id IN ",
        "<foreach item='id' collection='productIds' open='(' separator=',' close=')'>",
        "#{id}",
        "</foreach>",
        "</script>"
    })
    List<Product> findByIds(@Param("productIds") List<Long> productIds);
}