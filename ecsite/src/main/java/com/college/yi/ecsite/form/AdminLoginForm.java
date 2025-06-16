package com.college.yi.ecsite.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminLoginForm {
    
    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "有効なメールアドレスを入力してください")
    private String email;
    
    @NotBlank(message = "パスワードを入力してください")
    private String password;
} 