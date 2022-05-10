package com.example.demo.controller;


import com.example.demo.manage.RedisDataManage;
import com.example.demo.model.dto.LoginForm;
import com.example.demo.model.dto.RedisDataBean;
import com.example.demo.model.vo.Message;
import com.example.demo.utiles.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class TestURL {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisDataManage redisDataManage;

    @RequestMapping("/vue-element-admin/user/login")
    private Message testLogin(@RequestBody LoginForm loginForm){
        System.out.println(loginForm);

        Message<HashMap> message = new Message();
        message.initSuccessMessage();
        return message;
    }

    @RequestMapping("/testRedisList")
    private Message testRedisList(){
        ArrayList<Object> list = new ArrayList<>();

        for (int i =0;i<10;i++) {
            HashMap<String, Long> hashMap = new HashMap<>();
            hashMap.put("time",new Date().getTime());
            hashMap.put("key1", Long.valueOf(i));
            hashMap.put("key2",Long.valueOf(i*10));
            redisUtil.lSet("data",hashMap,3600);
            list.add(hashMap);
        }

//        redisUtil.lSet("data",list,3600);

        System.out.println(list);
        Message<Object> message = new Message<>();
        message.setData(list);
        message.initSuccessMessage();
        return message;
    }

    @RequestMapping("/testRedisListResize")
    private Message testRedisListResize(){

        long size = redisDataManage.resetRedisSize(redisUtil, "compressor-A", 5);

        Message<Object> message = new Message<>();
        message.setData(size);
        message.initSuccessMessage();
        return message;
    }

    @RequestMapping("/testRedisListGet")
    private Message testRedisListGet(){

        List<Object> list = redisUtil.lGet("data", 0, -1);

        for (Object obj : list) {
            HashMap<String, Long> hashMap = new HashMap((Map) obj);
            Set<String> keySet = hashMap.keySet();
            for (String key : keySet) {
                System.out.println("----------------------------------");
                System.out.println(hashMap.get(key));
                hashMap.put(key,hashMap.get(key)*100);
                System.out.println(hashMap.get(key));
                System.out.println("----------------------------------");
            }
        }
        System.out.println(list);
        Message<Object> message = new Message<>();
        message.setData(list);
        message.initSuccessMessage();
        return message;
    }


    @RequestMapping("/testGetAllRedisListData")
    private Message testGetAllRedisListData(){
        boolean b = redisDataManage.init();
        System.out.println(b);

        HashMap<String, List> listAllRedisData = redisDataManage.getListAllRedisData(redisUtil);
        System.out.println(listAllRedisData);

        Message<Object> message = new Message<>();
        message.setData(listAllRedisData);
        message.initSuccessMessage();
        return message;
    }

}
