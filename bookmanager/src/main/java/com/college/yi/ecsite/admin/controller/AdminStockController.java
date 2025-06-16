package com.college.yi.ecsite.admin.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.college.yi.ecsite.admin.form.StockUpdateForm;
import com.college.yi.ecsite.admin.repositoty.ProductMapper;
import com.college.yi.ecsite.admin.service.AdminStockService;
import com.college.yi.ecsite.entity.Product;
import com.college.yi.ecsite.exception.StockUpdateException;
//@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("api/admin/products")
public class AdminStockController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AdminStockService adminStockService;

    // 一覧画面の表示
    @GetMapping
    public String showStokList(@RequestParam(name = "continue", defaultValue = "1") int page, Model model) {
        int pageSize = 15;
        int offset = (page - 1) * pageSize;
        List<Product> products = productMapper.findAllNotDeleted(pageSize, offset);
        int total = productMapper.countAllNotDeleted();
        int totalPages = (int) Math.ceil((double) total / pageSize);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("stockUpdateForm", new StockUpdateForm());
        return "admin/stock_list"; // Thymeleaf等のHTMLテンプレート名
    }

    // 在庫の確認
    @PostMapping("/update")
    public String updateStock(@ModelAttribute("stockUpdateForm") @Valid StockUpdateForm form,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // 入力エラー時、再表示（商品一覧も再取得）
            List<Product> products = productMapper.findAllNotDeleted(15, 0);
            model.addAttribute("products", products);
            model.addAttribute("currentPage", 1);
            model.addAttribute("totalPages", 1);
            return "admin/stock_list";
        }
        try {
            adminStockService.updateStock(form.getProductId(), form.getStockQuantity());
            model.addAttribute("successMessage", "在庫を更新しました");
        } catch (StockUpdateException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        // 商品一覧の再取得（更新後の内容で再表示）
        List<Product> products = productMapper.findAllNotDeleted(15, 0);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", 1);
        model.addAttribute("stockUpdateForm", new StockUpdateForm());
        return "admin/stock_list";
    }
}
