package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.NoticeMapper;
import com.example.demo.model.entity.Notice;
import com.example.demo.model.vo.Message;
import com.example.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired(required = false)
    private NoticeMapper noticeMapper;

    @Override
    public Message getList() {
        QueryWrapper<Notice> qw = new QueryWrapper<>();
        List<Notice> notices = noticeMapper.selectList(qw);
        Message message = new Message();
        message.setData(notices);
        message.initSuccessMessage();
        System.out.println();
        System.out.println(message);
        System.out.println();
        return message;
    }
}
