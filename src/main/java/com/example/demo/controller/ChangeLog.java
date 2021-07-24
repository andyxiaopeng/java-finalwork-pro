package com.example.demo.controller;

import com.example.demo.entity.returnpojo.Message;
import com.example.demo.manage.returnmanage.MessageMnage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("changelog")
public class ChangeLog {

    @RequestMapping("getlist")
    private Message changeGetList(){
        Message<ArrayList> message = new Message<>();

        ArrayList<Map<String, String>> mapArrayList = new ArrayList<>();

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("content","修改changelog即可修改此处");
        stringStringHashMap.put("timestamp","最近更新");

        mapArrayList.add(stringStringHashMap);

        message.setData(mapArrayList);

        message.initSuccessEessage();
        return message;
    }
}
