package com.example.demo.controller;

import com.example.demo.model.vo.Message;
import com.example.demo.service.impl.ArithmeticShowImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("arithmeticShow")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ArithmeticShowController {

    @Autowired
    private ArithmeticShowImpl arithmeticShowImpl;

    @RequestMapping("getList")
    private Message arithmeticGetList(){
        return arithmeticShowImpl.getList();
    }

    @RequestMapping("doEdit")
    private Message arithmeticDoEdit(){
        return arithmeticShowImpl.doEdit();
    }
}
