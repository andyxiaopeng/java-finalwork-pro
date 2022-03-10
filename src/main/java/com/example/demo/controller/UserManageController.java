package com.example.demo.controller;

import com.example.demo.model.dto.LoginForm;
import com.example.demo.model.dto.UserManageForm;
import com.example.demo.model.vo.Message;
import com.example.demo.service.impl.UserManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userManagement")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserManageController {

    @Autowired(required = false)
    private UserManageServiceImpl userManageServiceImpl;

    @RequestMapping("getList")
    private Message getList(@RequestBody UserManageForm userManageForm){
        return userManageServiceImpl.getList(userManageForm);
    }

    @RequestMapping("doEdit")
    private Message doEdit(@RequestBody LoginForm loginForm){
        return userManageServiceImpl.doEdit(loginForm);
    }

    @RequestMapping("doDelete")
    private Message doDelete(){
        return userManageServiceImpl.doDelete();
    }

}
