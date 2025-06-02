package com.college.yi.ecsite.front.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.college.yi.ecsite.entity.CartItem;

@Mapper
public interface CartItemMapper {
    @Select("SELECT * FROM cart_items WHERE user_id = #{userId} AND product_id = #{productId}")
    CartItem findByUserIdAndProductId(Long userId, Long productId);
    
    @Select("SELECT * FROM cart_items WHERE user_id = #{userId}")
    List<CartItem> findByUserId(Long userId);

    @Insert("INSERT INTO cart_items (user_id, product_id, quantity, created_at, updated_at) " +
            "VALUES (#{userId}, #{productId}, #{quantity}, NOW(), NOW())")
    void insert(CartItem cartItem);

    @Update("UPDATE cart_items SET quantity = quantity + #{quantity}, updated_at = NOW() " +
            "WHERE user_id = #{userId} AND product_id = #{productId}")
    void updateQuantity(Long userId, Long productId, int quantity);
    
    @Update("UPDATE cart_items SET quantity = #{quantity}, updated_at = NOW() " +
            "WHERE user_id = #{userId} AND product_id = #{productId}")
    void updateQuantityAbsolute(Long userId, Long productId, int quantity);  // ★追加：絶対値更新

    @Delete("DELETE FROM cart_items WHERE user_id = #{userId} AND product_id = #{productId}")
    void deleteByUserIdAndProductId(Long userId, Long productId); // ★追加：削除
 }

