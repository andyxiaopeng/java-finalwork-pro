package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.entity.Notice;
import com.example.demo.model.vo.Message;

public interface NoticeService extends IService<Notice> {
    Message getList();
}
