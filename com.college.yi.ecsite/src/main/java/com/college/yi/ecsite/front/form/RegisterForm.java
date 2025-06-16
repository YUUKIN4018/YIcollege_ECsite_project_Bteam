package com.college.yi.ecsite.front.form;


import jakarta.validation.constraints.Email;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegisterForm {

	@NotBlank(message = "未入力があります")
	@Size(max = 50, message = "ユーザー名は50文字以内で入力してください")
	private String username;

	@NotBlank(message = "未入力があります")
	@Email(message = "メールアドレスの形式が正しくありません")
	private String email;

	@NotBlank(message = "未入力があります")
	@Size(min = 8, message = "パスワードは8文字以上で入力してください")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "パスワードは英字と数字を含めてください")
	private String password;

	@NotBlank(message = "未入力があります")
	private String passwordConfirm;



}
