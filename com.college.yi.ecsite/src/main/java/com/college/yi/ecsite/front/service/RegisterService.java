package com.college.yi.ecsite.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.front.form.RegisterForm;
import com.college.yi.ecsite.front.repository.UserMapper;

@Service
public class RegisterService {

	@Autowired

	private UserMapper userMapper;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public void registerUser(RegisterForm form) {
		User existingUser = userMapper.findByEmail(form.getEmail());
		if (existingUser != null) {
			throw new IllegalArgumentException("このメールアドレスはすでに登録されています。");

		}

		User user = new User();
		user.setEmail(form.getEmail());
		user.setPassword_hash(passwordEncoder.encode(form.getPassword()));
		user.setLast_name(form.getUsername());
		user.setFirst_name(form.getUsername());

	}

}
