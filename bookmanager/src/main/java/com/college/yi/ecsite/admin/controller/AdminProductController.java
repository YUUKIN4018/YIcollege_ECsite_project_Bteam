package com.college.yi.ecsite.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.yi.ecsite.admin.service.AdminProductService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class AdminProductController {
    
    private final AdminProductService adminProductService;
    private static final int PAGE_SIZE = 15;
    
    @GetMapping("/admin/products")
    public String showProductList(@RequestParam(name = "page", defaultValue = "1")int page,Model model) {
        //商品一覧データ取得
        var productList = adminProductService.getProductList(page, PAGE_SIZE);
        model.addAttribute("productList", productList);
        model.addAttribute("currentPage", page);
        return "admin/products";
    }
}
