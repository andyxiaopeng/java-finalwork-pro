package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoginForm {
    private String username;
    private String password;

    private String email;

    private List<String> permissions;

    private String accessToken;

    private String param;// RSA 加密后的字符串
}
