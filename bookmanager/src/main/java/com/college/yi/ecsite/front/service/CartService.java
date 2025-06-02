package com.college.yi.ecsite.front.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.yi.ecsite.entity.CartItem;
import com.college.yi.ecsite.entity.Product;
import com.college.yi.ecsite.exception.CartItemNotFoundException;
import com.college.yi.ecsite.exception.OutOfStockException;
import com.college.yi.ecsite.front.dto.CartItemDto;
import com.college.yi.ecsite.front.repository.CartItemMapper;
import com.college.yi.ecsite.front.repository.ProductMapper;

@Service
public class CartService {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ProductMapper productMapper;

    public void addToCart(Long userId, Long productId, int quantity) {
        Product product = productMapper.findById(productId);
        if (product == null || product.getStockQuantity() < quantity) {
            throw new OutOfStockException("在庫が足りません。");
        }

        CartItem existing = cartItemMapper.findByUserIdAndProductId(userId, productId);
        if (existing != null) {
            cartItemMapper.updateQuantity(userId, productId, quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUserId(userId);
            newItem.setProductId(productId);
            newItem.setQuantity(quantity);
            cartItemMapper.insert(newItem);
        }
    }
    
    public List<CartItemDto> getCartItems(Long userId) {
        List<CartItem> items = cartItemMapper.findByUserId(userId);
        List<CartItemDto> result = new ArrayList<>();

        for (CartItem item : items) {
            Product product = productMapper.findById(item.getProductId());
            if (product == null) continue;

            CartItemDto dto = new CartItemDto();
            dto.setProductId(product.getProductId());
            dto.setProductName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setQuantity(item.getQuantity());
            dto.setImageUrl(dto.getImageUrl()); // 画像URLが存在する前提
            dto.setSubtotal(product.getPrice() * item.getQuantity());

            result.add(dto);
        }

        return result;
    }

    public void updateCartItems(Long userId, Map<Long, Integer> quantities) {
        for (Map.Entry<Long, Integer> entry : quantities.entrySet()) {
            Long productId = entry.getKey();
            int newQuantity = entry.getValue();

            Product product = productMapper.findById(productId);
            if (product == null) {
                throw new CartItemNotFoundException("商品が見つかりません: ID=" + productId);
            }

            if (product.getStockQuantity() < newQuantity) {
                throw new OutOfStockException("在庫が足りません: " + product.getName());
            }

            CartItem item = cartItemMapper.findByUserIdAndProductId(userId, productId);
            if (item == null) {
                throw new CartItemNotFoundException("カート内に商品がありません: ID=" + productId);
            }

            cartItemMapper.updateQuantity(userId, productId, newQuantity);
        }
    }

    public void deleteCartItem(Long userId, Long productId) {
        CartItem item = cartItemMapper.findByUserIdAndProductId(userId, productId);
        if (item == null) {
            throw new CartItemNotFoundException("カート内に商品が見つかりません");
        }

        cartItemMapper.deleteByUserIdAndProductId(userId, productId);
    }

    public int calculateTotal(List<CartItemDto> cartItems) {
        return cartItems.stream()
                        .mapToInt(CartItemDto::getSubtotal)
                        .sum();
    }
}