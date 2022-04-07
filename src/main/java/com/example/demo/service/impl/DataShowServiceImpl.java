package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.manage.RedisDataManage;
import com.example.demo.mapper.DataShowMapper;
import com.example.demo.model.dto.RedisDataBean;
import com.example.demo.model.entity.DataShow;
import com.example.demo.model.vo.Message;
import com.example.demo.service.DataShowService;
import com.example.demo.utiles.redis.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Random;

@Service
public class DataShowServiceImpl extends ServiceImpl<DataShowMapper, DataShow> implements DataShowService {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Message getList() {
        Message message = new Message();

        RedisDataBean allRedisData = RedisDataManage.getAllRedisData(redisUtil);
        HashMap<String, Integer> data = allRedisData.getData();

        message.setData(data);

        message.initSuccessMessage();
        return message;
    }
}
