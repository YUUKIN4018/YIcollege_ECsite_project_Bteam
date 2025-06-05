package com.college.yi.bookmanager.repository;

import org.apache.ibatis.annotations.Mapper;

import com.college.yi.bookmanager.entity.UserEntity;

@Mapper
public interface UserMapper {
    UserEntity findByUsername(String username);
}
