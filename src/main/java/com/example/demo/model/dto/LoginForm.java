package com.example.demo.model.dto;

import lombok.Data;

@Data
public class LoginForm {
    private String username;
    private String password;

    private String accessToken;

    private String param;// RSA 加密后的字符串
}
