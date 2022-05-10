package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.entity.ArithmeticShow;
import com.example.demo.model.vo.Message;

public interface ArithmeticShowService extends IService<ArithmeticShow> {
    Message getList();
    Message doEdit();
}
