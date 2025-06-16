package com.college.yi.ecsite.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.exception.DuplicateEmailException;
import com.college.yi.ecsite.front.form.UserProfileForm;
import com.college.yi.ecsite.front.repository.UserMapper;

@Service
public class UserProfileService {

	@Autowired
	private UserMapper userMapper;

	public UserProfileForm getUserProfileForm(Integer userId) {
		User user = userMapper.findById(userId);
		if (user == null) {
			throw new RuntimeException("ユーザー情報を取得できません");
		}

		UserProfileForm form = new UserProfileForm();
		form.setEmail(user.getEmail());
		form.setLastName(user.getLast_name());
		form.setFirstName(user.getFirst_name());
		form.setLastNameKana(user.getLast_name_kana());
		form.setFirstNameKana(user.getFirst_name_kana());
		form.setZipCode(user.getZip_code());
		form.setAddress(user.getAddress());

		return form;

	}

	public void updateUserProfile(UserProfileForm form, Integer userId) {
		User existing = userMapper.findByEmail(form.getEmail());
		if (existing != null && !existing.getUser_id().equals(userId)) {
			throw new DuplicateEmailException("すでに登録済みのメールアドレスです");

		}

		User user = new User();
		user.setUser_id(userId);
		user.setEmail(form.getEmail());
		user.setLast_name(form.getLastName());
		user.setFirst_name(form.getFirstName());
		user.setLast_name_kana(form.getLastNameKana());
		user.setFirst_name_kana(form.getFirstNameKana());
		user.setZip_code(form.getZipCode());
		user.setAddress(form.getAddress());


		userMapper.update(user);
	}
}
