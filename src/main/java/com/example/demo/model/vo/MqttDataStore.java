package com.example.demo.model.vo;

import java.util.HashMap;


public class MqttDataStore {
    public static HashMap<String, HashMap<String,Integer>> data;

    public static void setDataValue(String deviceId,String attribute,Integer value){
        HashMap<String,Integer> hashMap = new HashMap();
        hashMap.put(attribute,value);
        data.put(deviceId, hashMap);
    }

    public static HashMap<String, HashMap<String,Integer>> getDataValue(){
        return data;
    }
}
