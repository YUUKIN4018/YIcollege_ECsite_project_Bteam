package com.college.yi.ecsite.front.form;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileForm {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(max = 50)
	private String lastName;

	@NotBlank
	@Size(max = 50)
	private String firstName;

	@Pattern(regexp = "^[\\u30A0-\\u30FF]+$", message = "セイはカタカナで入力してください")
	private String lastNameKana;

	@Pattern(regexp = "^[\\u30A0-\\u30FF]+$", message = "メイはカタカナで入力してください")
	private String firstNameKana;

	@Pattern(regexp = "^\\d{3}-\\d{4}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
	private String zipCode;

	private String address;
}
