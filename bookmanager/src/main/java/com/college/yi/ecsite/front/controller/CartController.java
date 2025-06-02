package com.college.yi.ecsite.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.college.yi.ecsite.exception.NotLoggedInException;
import com.college.yi.ecsite.exception.OutOfStockException;
import com.college.yi.ecsite.front.dto.CartItemDto;
import com.college.yi.ecsite.front.form.CartAddForm;
import com.college.yi.ecsite.front.form.CartUpdateForm;
import com.college.yi.ecsite.front.service.CartService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@Valid CartAddForm form, BindingResult result,
                            @AuthenticationPrincipal(expression = "id") Long userId,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "redirect:/product/" + form.getProductId(); // バリデーションエラー時は商品詳細に戻す
        }

        if (userId == null) {
            throw new NotLoggedInException("ログインしていません");
        }

        try {
            cartService.addToCart(userId, form.getProductId(), form.getQuantity());
            return "redirect:/cart/added";
        } catch (OutOfStockException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/product/" + form.getProductId(); // 在庫エラー時も詳細画面へ戻す
        }
    }
    @GetMapping
    public String showCart(@AuthenticationPrincipal(expression = "id") Long userId, Model model) {
        if (userId == null) {
            throw new NotLoggedInException("ログインしていません");
        }
        List<CartItemDto> cartItems = cartService.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", cartService.calculateTotal(cartItems));
        return "cart/view"; // cart/view.html に一覧を表示
    }

    @PostMapping("/update")
    public String updateCart(@AuthenticationPrincipal(expression = "id") Long userId,
                             @ModelAttribute CartUpdateForm form,
                             RedirectAttributes redirectAttributes) {
        if (userId == null) {
            throw new NotLoggedInException("ログインしていません");
        }

        try {
            cartService.updateCartItems(userId, form.getProductQuantities());
        } catch (OutOfStockException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/cart";
    }

    @PostMapping("/delete")
    public String deleteCartItem(@AuthenticationPrincipal(expression = "id") Long userId,
                                 @RequestParam("productId") Long productId) {
        if (userId == null) {
            throw new NotLoggedInException("ログインしていません");
        }

        cartService.deleteCartItem(userId, productId);
        return "redirect:/cart";
    }
    
}
