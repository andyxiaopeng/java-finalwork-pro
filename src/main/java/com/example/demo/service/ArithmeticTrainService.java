package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.entity.ArithmeticTrain;
import com.example.demo.model.vo.Message;

public interface ArithmeticTrainService extends IService<ArithmeticTrain> {
    Message getList();
    Message doEdit();
}
