package com.example.demo.manage;

import com.example.demo.utiles.mqtt.objects.MessageListener;
import com.example.demo.utiles.mqtt.util.MqttUtil;
import com.example.demo.utiles.redis.util.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Component
public class MqttClientManage {

    @Resource
    private RedisUtil redisUtil;

    private static String topic = "hydrogen/#";

    @PostConstruct
    public void autoCreateMqttListener(){
        System.out.println("Mqtt--------------------------------------------");
        MqttUtil.subscribe(topic, new MessageListener());
        System.out.println("Mqtt--------------------------------------------");
    }
}
