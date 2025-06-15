package com.college.yi.ecsite.admin.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.college.yi.ecsite.admin.form.ProductRegisterForm;
import com.college.yi.ecsite.admin.repositoty.ProductImageMapper;
import com.college.yi.ecsite.admin.repositoty.ProductMapper;
import com.college.yi.ecsite.entity.Product;
import com.college.yi.ecsite.entity.ProductImage;
import com.college.yi.ecsite.exception.ProductRegistrationException;

@Service
public class AdminProductRegisterService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImageMapper productImageMapper;

    public void registerProduct(ProductRegisterForm form) throws ProductRegistrationException {
        // 商品本体を登録
        Product product = new Product();
        try {
            productMapper.insert(product);
        } catch (Exception e) {
            throw new ProductRegistrationException("商品情報のDB登録に失敗しました。");
        }

        // 画像保存（省略：ファイル保存処理）
        if (form.getImage() != null && !form.getImage().isEmpty()) {
            String fileName = saveImage(form.getImage()); // ここで例外が投げられる
            ProductImage image = new ProductImage();
            try {
                productImageMapper.insert(image);
            } catch (Exception e) {
                throw new ProductRegistrationException("画像情報のDB登録に失敗しました。");
            }
        }
    }

    private String saveImage(MultipartFile image) throws ProductRegistrationException {
        // ファイル拡張子チェック
        String originalName = image.getOriginalFilename();
        if (originalName == null || !originalName.matches(".*\\.(jpg|png|gif)$")) {
            throw new ProductRegistrationException("対応形式ではありません（jpg/png/gifのみ）");
        }
        // 容量チェック
        if (image.getSize() > 5 * 1024 * 1024) {
            throw new ProductRegistrationException("画像サイズが大きすぎます（最大5MB）");
        }
        // 保存処理（ここでは仮のファイル名を返すだけ）
        // 実際はサーバーのディレクトリに保存するコードが必要です
        return "/images/" + originalName;
    }
}
