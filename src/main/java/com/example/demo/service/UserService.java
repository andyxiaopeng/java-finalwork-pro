package com.example.demo.service;

import com.example.demo.model.dto.LoginForm;
import com.example.demo.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.vo.Message;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyh
 * @since 2021-07-24
 */
public interface UserService extends IService<User> {
    Message getPublicKey();
    Message login(LoginForm loginForm);
    Message getUserInfo(LoginForm loginForm);
    Message logout();


}
