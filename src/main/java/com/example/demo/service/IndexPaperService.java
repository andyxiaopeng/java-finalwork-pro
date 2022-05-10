package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.entity.IndexData;
import com.example.demo.model.vo.Message;

public interface IndexPaperService extends IService<IndexData> {
    Message getList();
}
