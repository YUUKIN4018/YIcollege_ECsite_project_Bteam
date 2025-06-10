package com.college.yi.ecsite.front.repository;

import org.apache.ibatis.annotations.Mapper;

import com.college.yi.ecsite.entity.User;

@Mapper
public interface UserMapper {

	void insert(User user);

	User findByEmail(String email);

	User findById(Integer userId);

	void update(User user);

}
