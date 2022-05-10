package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.manage.ArithmeticTrainManage;
import com.example.demo.mapper.ArithmeticTrainMapper;
import com.example.demo.model.entity.ArithmeticTrain;
import com.example.demo.model.vo.Message;
import com.example.demo.service.ArithmeticTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArithmeticTrainImpl extends ServiceImpl<ArithmeticTrainMapper, ArithmeticTrain> implements ArithmeticTrainService {

    @Autowired(required = false)
    private ArithmeticTrainManage arithmeticTrainManage;

    @Autowired(required = false)
    private ArithmeticTrainMapper arithmeticTrainMapper;

    @Override
    public Message getList() {
        Message<Object> message = new Message<>();
        ArithmeticTrain item = new ArithmeticTrainManage().getDataItem(arithmeticTrainMapper);
        message.setData(item);
        message.initSuccessMessage();
        return message;
    }

    @Override
    public Message doEdit() {
        Message<Object> message = new Message<>();
        int item = new ArithmeticTrainManage().setFlag(0);
        message.setData(item);
        message.initSuccessMessage();
        return message;
    }
}
