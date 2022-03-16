package com.example.demo.controller;

import com.example.demo.model.dto.UserManageForm;
import com.example.demo.model.vo.Message;
import com.example.demo.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notice")
@CrossOrigin(origins = "*",maxAge = 3600)
public class Notice {

    @Autowired(required = false)
    private NoticeServiceImpl noticeServiceImpl;

    @RequestMapping("getList")
    private Message getList(){
        return noticeServiceImpl.getList();
    }
}
