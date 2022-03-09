package com.example.demo.controller;


import com.example.demo.mapper.UserMapper;
import com.example.demo.model.entity.User;
import com.example.demo.model.dto.LoginForm;
import com.example.demo.model.vo.Message;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyh
 * @since 2021-07-24
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    @Autowired(required = false)
    private UserServiceImpl userServiceImpl;

    /**
     * 实现登录加密 或者是 实现 服务端认证功能
     * 暂未实际使用
     * @return
     */
    @RequestMapping("publicKey")
    private Message getPublicKey(){
        Message message = userServiceImpl.getPublicKey();
        return message;
    }

    /**
     * 实现登录功能
     * @param loginForm
     * @return
     */
    @RequestMapping("login")
    public Message login(@RequestBody LoginForm loginForm){
        Message message = userServiceImpl.login(loginForm);
        return message;
    }

    /**
     * 实现获取账号信息功能
     * @param loginForm
     * @return
     */
    @RequestMapping("userInfo")
    public Message userInfo(@RequestBody LoginForm loginForm){
        Message message = userServiceImpl.getUserInfo(loginForm);
        return message;
    }

    /**
     * 实现登出功能
     * @return
     */
    @RequestMapping("logout")
    private Message logout(){
        Message message = userServiceImpl.logout();
        return message;
    }

}

