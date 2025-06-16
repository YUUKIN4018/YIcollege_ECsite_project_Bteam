package com.college.yi.ecsite.admin.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductRegisterForm {

    @NotBlank(message = "商品名は必須です")
    @Size(max = 50, message = "商品名は50文字以内で入力してください")
    private String name;
    
    @NotNull(message = "価格は必須です")
    @Min(value = 0, message = "価格は0以上で入力してください")
    @Max(value = 1000000,message = "価格は100万円未満で入力してください")
    private Integer price;
    
    @NotNull(message = "カテゴリを選択してください")
    private Long categoryId;
    
    private String description;
    
    private MultipartFile image;
}
