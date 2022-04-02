package com.example.demo.utiles.mqtt.objects;

import com.example.demo.utiles.mqtt.util.MqttUtil;

public class MessageSender {
    public static void main(String[] args) {
        MqttUtil.send("send01","I am java of springboot and Hello Wolrd!");
    }
}
