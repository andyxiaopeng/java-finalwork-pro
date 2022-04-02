package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.entity.DataShow;
import com.example.demo.model.vo.Message;

public interface DataShowService extends IService<DataShow> {
    Message getList();
}
