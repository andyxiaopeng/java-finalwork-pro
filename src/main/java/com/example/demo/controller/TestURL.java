package com.example.demo.controller;


import com.example.demo.model.dto.LoginForm;
import com.example.demo.model.vo.Message;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class TestURL {

    @RequestMapping("/vue-element-admin/user/login")
    private Message testLogin(@RequestBody LoginForm loginForm){
        System.out.println(loginForm);

        Message<HashMap> message = new Message();
        message.initSuccessEessage();
        return message;
    }
}
