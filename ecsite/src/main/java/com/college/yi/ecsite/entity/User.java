package com.college.yi.ecsite.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String email;
    private String passwordHash;
    private String lastName;
    private String firstName;
    private String lastNameKana;
    private String firstNameKana;
    private LocalDate birthDate;
    private Integer gender;
    private Integer status;
    private Integer role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;
} 