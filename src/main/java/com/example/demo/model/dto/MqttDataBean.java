package com.example.demo.model.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class MqttDataBean {
//    { "deviceId": "d1","code":"200","data":{"values":"999"} }

    private String deviceId;

    private String timeStamp;

    private String code;

    private HashMap<String,String> data;
}
