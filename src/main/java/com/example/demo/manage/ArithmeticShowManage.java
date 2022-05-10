package com.example.demo.manage;

import com.example.demo.mapper.ArithmeticShowMapper;
import com.example.demo.model.entity.ArithmeticShow;

import java.util.HashMap;

public class ArithmeticShowManage {

    private static int itemID = 1;

    private static int flag = 0;


    public HashMap<String, ArithmeticShow> getDataItems(ArithmeticShowMapper arithmeticShowMapper){
        if (flag == 0){
            itemID = 1;
            flag = 1;
        }
        int real_index = itemID - 30;

        HashMap<String, ArithmeticShow> hashMap = new HashMap<>();

        ArithmeticShow arithmeticShow = arithmeticShowMapper.selectById(itemID);
        hashMap.put("prediction",arithmeticShow);
        itemID ++;


        if(real_index >0){
            arithmeticShow = arithmeticShowMapper.selectById(real_index);
            hashMap.put("real",arithmeticShow);
        }
        return hashMap;
    }

    public int setFlag(int x){
        flag = x;
        return flag;
    }

}
