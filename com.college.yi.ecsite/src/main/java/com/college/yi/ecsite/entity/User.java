package com.college.yi.ecsite.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private Integer user_id;
	private String email;
	private String password_hash;
	private String last_name;
	private String first_name;
	private LocalDate birth_date;
	private String gender;
	private Integer status;
	private Integer role;
	private String created_at;
	private String updated_at;
	private String last_login_at;
	private String last_name_kana;
	private String first_name_kana;
	private String zip_code;
	private String address;

}