package com.example.demo.controller;

import com.example.demo.model.vo.Message;
import com.example.demo.service.impl.ArithmeticTrainImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("arithmeticTrain")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ArithmeticTrainController {
    @Autowired
    private ArithmeticTrainImpl arithmeticTrainImpl;

    @RequestMapping("getList")
    private Message arithmeticGetList(){
        return arithmeticTrainImpl.getList();
    }

    @RequestMapping("doEdit")
    private Message arithmeticDoEdit(){
        return arithmeticTrainImpl.doEdit();
    }
}
