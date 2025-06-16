package com.college.yi.ecsite.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        
        log.error("エラー発生: status={}, message={}", status, message);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("error", "アクセス権限がありません");
                return "error/403";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("error", "ページが見つかりません");
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("error", "サーバーエラーが発生しました");
                return "error/500";
            }
        }
        
        model.addAttribute("error", "予期せぬエラーが発生しました");
        return "error/error";
    }
} 