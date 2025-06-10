package com.college.yi.ecsite.front.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.front.dto.MyPageDto;
import com.college.yi.ecsite.front.repository.UserMapper;

@Service
public class MyPageService {

	@Autowired
	private UserMapper userMapper;

	public MyPageDto getUserInfoForMyPage(Integer userId) {

		User user = userMapper.findById(userId);
		if (user == null)
			throw new RuntimeException("ユーザー情報が見つかりませんでした");

		MyPageDto dto = new MyPageDto();
		dto.setUserName(user.getLast_name() + " " + user.getFirst_name());

		return dto;

	}

}
