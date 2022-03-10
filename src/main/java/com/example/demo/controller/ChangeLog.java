package com.example.demo.controller;

import com.example.demo.model.vo.Message;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("changeLog")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ChangeLog {

    @RequestMapping("getList")
    private Message changeGetList(){
        Message<ArrayList> message = new Message<>();

        ArrayList<Map<String, String>> mapArrayList = new ArrayList<>();

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("content","修改changelog即可修改此处");
        stringStringHashMap.put("timestamp","最近更新");

        mapArrayList.add(stringStringHashMap);

        message.setData(mapArrayList);

        message.initSuccessMessage();
        return message;
    }
}
