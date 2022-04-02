package com.example.demo.utiles.mqtt.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class MqttProperties {
    private String username = "andy";

    private String password = "123456";

    private String Address = "tcp://116.62.115.28:1883";

    private List<Integer> qos;

    private Integer keepAlive = 60;

    private Integer completionTime = 1000;

    private List<String> topics;

}
