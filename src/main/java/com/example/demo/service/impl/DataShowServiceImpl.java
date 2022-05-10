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
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class DataShowServiceImpl extends ServiceImpl<DataShowMapper, DataShow> implements DataShowService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisDataManage redisDataManage;

    @Override
    public Message getList() {
        Message message = new Message();

        HashMap<String, List> listAllRedisData = redisDataManage.getListAllRedisData(redisUtil);
        System.out.println(listAllRedisData);

        message.setData(listAllRedisData);

        message.initSuccessMessage();
        return message;
    }

//    @Override
//    public Message getList() {
//        Message message = new Message();
//
//        RedisDataBean allRedisData = redisDataManage.getAllRedisData(redisUtil);
//        HashMap<String, Integer> data = allRedisData.getData();
//
//        message.setData(data);
//
//        message.initSuccessMessage();
//        return message;
//    }
}
