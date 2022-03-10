package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.dto.LoginForm;
import com.example.demo.model.dto.UserManageForm;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.Message;

public interface UserManageService extends IService<User> {
    Message getList(UserManageForm userManageForm);
    Message doEdit(LoginForm loginForm);
    Message doDelete();
}
