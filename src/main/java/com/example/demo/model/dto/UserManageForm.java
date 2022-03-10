package com.example.demo.model.dto;

import lombok.Data;

@Data
public class UserManageForm {
    private String username;
    private int pageNo;
    private int pageSize;
    private String title;
}
