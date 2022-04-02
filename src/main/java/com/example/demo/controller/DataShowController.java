package com.example.demo.controller;

import com.example.demo.model.vo.Message;
import com.example.demo.service.impl.DataShowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("dataShow")
@CrossOrigin(origins = "*",maxAge = 3600)
public class DataShowController {

    @Autowired(required = false)
    private DataShowServiceImpl dataShowServiceImpl;


    @RequestMapping("getList")
    private Message dataShwoGetList() {
        return dataShowServiceImpl.getList();
    }
}
