package com.college.yi.bookmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 認可ルール
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/books").hasAnyRole("USER", "ADMIN") 
                .requestMatchers("/api/books/**").hasRole("ADMIN")         
                .anyRequest().permitAll()
            )

            // ログインフォーム（デフォルト）
            .formLogin(form -> form
                .loginPage("/login")      
                .defaultSuccessUrl("/books", true)
                .failureUrl("/login?error")
                .permitAll()
            )

            // ログアウト（必要に応じて）
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )

            // CSRF（デフォルトで有効）
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**") 
            );

        return http.build();
    }
}
