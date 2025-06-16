package com.college.yi.ecsite.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.yi.ecsite.admin.repositoty.ProductMapper;
import com.college.yi.ecsite.entity.Product;
import com.college.yi.ecsite.exception.StockUpdateException;

@Service
public class AdminStockService {

    @Autowired
    private ProductMapper productMapper;

    // 特定の商品の在庫を更新
    @Transactional
    public void updateStock(Long productId, Integer stockQuantity) {
        // 商品IDが存在するかの確認
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new StockUpdateException("対象商品が見つかりません");
        }

        // 在庫数入力
        if (stockQuantity == null || stockQuantity < 0) {
            throw new StockUpdateException("在庫数は0以上の数字を入力してください");
        }

        // 更新
        int updated = productMapper.updateStockQuantity(productId, stockQuantity);
        if (updated == 0) {
            throw new StockUpdateException("在庫更新に失敗しました");
        }
    }

}
