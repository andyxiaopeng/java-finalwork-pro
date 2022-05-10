package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.manage.ArithmeticShowManage;
import com.example.demo.manage.ArithmeticTrainManage;
import com.example.demo.mapper.ArithmeticShowMapper;
import com.example.demo.model.entity.ArithmeticShow;
import com.example.demo.model.vo.Message;
import com.example.demo.service.ArithmeticShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ArithmeticShowImpl extends ServiceImpl<ArithmeticShowMapper, ArithmeticShow> implements ArithmeticShowService {

    @Autowired(required = false)
    private ArithmeticShowMapper arithmeticShowMapper;

    @Override
    public Message getList() {
        Message<Object> message = new Message<>();
        HashMap<String, ArithmeticShow> items = new ArithmeticShowManage().getDataItems(arithmeticShowMapper);
        message.setData(items);
        message.initSuccessMessage();
        return message;
    }

    @Override
    public Message doEdit() {
        Message<Object> message = new Message<>();
        int item = new ArithmeticShowManage().setFlag(0);
        message.setData(item);
        message.initSuccessMessage();
        return message;
    }
}
