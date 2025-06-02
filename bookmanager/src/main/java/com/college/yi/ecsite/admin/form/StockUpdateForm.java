package com.college.yi.ecsite.admin.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StockUpdateForm {
    @NotNull(message = "商品IDは必須です")
    private Long productId;
    
    @NotNull(message = "在庫数は必須です")
    @Min(value = 0, message = "在庫数は0以上で入力してください")
    private Integer stockQuantity;

}
