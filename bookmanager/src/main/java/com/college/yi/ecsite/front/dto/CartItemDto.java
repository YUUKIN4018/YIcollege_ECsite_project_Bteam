package com.college.yi.ecsite.front.dto;

import lombok.Data;

@Data
public class CartItemDto {

	 private Long productId;
	    private String productName;
	    private int price;
	    private int quantity;
	    private int subtotal;
	    private String imageUrl;
	    
	 
	    }

