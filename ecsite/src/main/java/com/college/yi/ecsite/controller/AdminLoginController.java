package com.college.yi.ecsite.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.college.yi.ecsite.form.AdminLoginForm;
import com.college.yi.ecsite.service.AdminLoginService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminLoginController {

    private final AdminLoginService adminLoginService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("adminLoginForm", new AdminLoginForm());
        return "admin/login";
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        log.debug("ダッシュボードページを表示");
        return "admin/dashboard";
    }

    @PostMapping("/login/process")
    public String processLogin(@Valid AdminLoginForm form, BindingResult result) {
        if (result.hasErrors()) {
            log.warn("バリデーションエラー: {}", result.getAllErrors());
            return "admin/login";
        }

        try {
            Authentication authentication = adminLoginService.authenticate(form);
            if (authentication != null && authentication.isAuthenticated()) {
                log.info("管理者ログイン成功: {}", form.getEmail());
                return "redirect:/admin/dashboard";
            }
        } catch (Exception e) {
            log.error("ログイン処理でエラー発生: {}", e.getMessage());
            result.rejectValue("email", "error.adminLoginForm", "メールアドレスまたはパスワードが正しくありません");
        }

        return "admin/login";
    }
} 