package com.college.yi.ecsite.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.form.AdminLoginForm;
import com.college.yi.ecsite.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminLoginService {

    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Transactional(readOnly = true)
    public Authentication authenticate(AdminLoginForm form) {
        log.debug("認証処理開始: {}", form.getEmail());
        
        // ユーザー情報の取得（管理者権限の確認のみ）
        User user = userMapper.findByEmailAndRoleAdmin(form.getEmail());
        if (user == null) {
            log.warn("管理者権限のないユーザーによるログイン試行: {}", form.getEmail());
            throw new RuntimeException("管理者権限がありません");
        }

        try {
            // Spring Securityの認証処理を実行
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword())
            );
            
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("認証成功: {}", form.getEmail());
                return authentication;
            } else {
                log.warn("認証失敗: {}", form.getEmail());
                throw new RuntimeException("認証に失敗しました");
            }
        } catch (Exception e) {
            log.error("認証処理でエラー発生: {}", e.getMessage());
            throw new RuntimeException("認証処理中にエラーが発生しました", e);
        }
    }
} 