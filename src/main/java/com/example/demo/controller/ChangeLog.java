package com.example.demo.controller;

import com.example.demo.model.vo.Message;
import com.example.demo.service.impl.ChangeLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("changeLog")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ChangeLog {

    @Autowired(required = false)
    private ChangeLogServiceImpl changeLogServiceImpl;

    @RequestMapping("getList")
    private Message changeGetList(){
        return changeLogServiceImpl.getList();
    }
}
