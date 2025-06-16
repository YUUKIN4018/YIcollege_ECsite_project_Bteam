package com.college.yi.ecsite.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("ユーザー認証開始: {}", email);
        
        User user = userMapper.findByEmailAndRoleAdmin(email);
        if (user == null) {
            log.warn("ユーザーが見つかりません: {}", email);
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + email);
        }

        if (user.getPasswordHash() == null || user.getPasswordHash().isEmpty()) {
            log.error("パスワードハッシュが設定されていません: {}", email);
            throw new UsernameNotFoundException("パスワードが設定されていません");
        }

        log.debug("ユーザー認証成功: {}", email);
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPasswordHash())
            .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
            .build();
    }
} 