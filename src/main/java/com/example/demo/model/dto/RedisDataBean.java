package com.example.demo.model.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class RedisDataBean {
    private HashMap<String,Integer> data;
}
