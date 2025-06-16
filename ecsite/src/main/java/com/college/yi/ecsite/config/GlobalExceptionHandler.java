package com.college.yi.ecsite.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.college.yi.ecsite.exception.AuthorizationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ModelAndView handleAuthenticationException(AuthenticationException e) {
        log.warn("認証エラー: {}", e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", e.getMessage());
        mav.setViewName("error/403");
        return mav;
    }

    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView handleAuthorizationException(AuthorizationException e) {
        log.warn("認可エラー: {}", e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", e.getMessage());
        mav.setViewName("error/403");
        return mav;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(AccessDeniedException e) {
        log.warn("アクセス拒否: {}", e.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "このページにアクセスする権限がありません。");
        mav.setViewName("error/403");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        log.error("予期せぬエラーが発生しました", e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "予期せぬエラーが発生しました。");
        mav.setViewName("error/500");
        return mav;
    }
} 