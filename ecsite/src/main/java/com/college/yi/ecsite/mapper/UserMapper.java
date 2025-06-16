package com.college.yi.ecsite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.college.yi.ecsite.entity.User;

@Mapper
public interface UserMapper {
    
    @Select("SELECT user_id, email, password_hash, last_name, first_name, last_name_kana, first_name_kana, birth_date, gender, status, role, created_at, updated_at, last_login_at FROM users WHERE email = #{email} AND role = 1 AND status = 1")
    @Results({
        @Result(property = "userId", column = "user_id"),
        @Result(property = "passwordHash", column = "password_hash"),
        @Result(property = "lastName", column = "last_name"),
        @Result(property = "firstName", column = "first_name"),
        @Result(property = "lastNameKana", column = "last_name_kana"),
        @Result(property = "firstNameKana", column = "first_name_kana"),
        @Result(property = "birthDate", column = "birth_date"),
        @Result(property = "createdAt", column = "created_at"),
        @Result(property = "updatedAt", column = "updated_at"),
        @Result(property = "lastLoginAt", column = "last_login_at")
    })
    User findByEmailAndRoleAdmin(String email);
} 