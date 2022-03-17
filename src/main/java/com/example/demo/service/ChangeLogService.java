package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.entity.ChangeLog;
import com.example.demo.model.vo.Message;

public interface ChangeLogService extends IService<ChangeLog> {
    Message getList();
    void insertChangeLog(String username, String action);
}
