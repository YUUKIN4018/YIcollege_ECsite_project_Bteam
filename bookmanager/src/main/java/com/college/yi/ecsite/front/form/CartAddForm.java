package com.college.yi.ecsite.front.form;

import java.util.Map;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartAddForm {
    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;
    
    // productId をキー、数量を値とするマップ
    @NotNull
    private Map<@NotNull Long, @Min(0) Integer> quantities;

}
