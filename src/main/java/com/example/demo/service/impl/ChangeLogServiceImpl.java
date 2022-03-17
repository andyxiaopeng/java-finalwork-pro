package com.example.demo.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.ChangeLogMapper;
import com.example.demo.model.entity.ChangeLog;
import com.example.demo.model.vo.Message;
import com.example.demo.service.ChangeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChangeLogServiceImpl extends ServiceImpl<ChangeLogMapper, ChangeLog> implements ChangeLogService {

    @Autowired(required = false)
    private ChangeLogMapper changeLogMapper;

    @Override
    public Message getList() {
        Message message = new Message();

        List<ChangeLog> changeLogs = changeLogMapper.selectList(null);
        message.setData(changeLogs);
        message.initSuccessMessage();

        return message;
    }

    @Override
    public void insertChangeLog(String username, String action) {
        // 创建文本
        String content = username.concat("：").concat(action);
        // 创建时间
        DateUtil dateUtil = new DateUtil();
        String timestamp = dateUtil.now();

        ChangeLog changeLog = new ChangeLog();
        changeLog.setContent(content);
        changeLog.setTimestamp(timestamp);

        System.out.println(changeLog);

        // 写入数据库
        changeLogMapper.insert(changeLog);
    }
}
