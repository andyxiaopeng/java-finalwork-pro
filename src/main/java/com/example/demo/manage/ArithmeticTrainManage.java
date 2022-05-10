package com.example.demo.manage;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.ArithmeticTrainMapper;
import com.example.demo.model.entity.ArithmeticTrain;

import java.sql.Wrapper;

public class ArithmeticTrainManage {
    /**
     * 这是模拟训练数据的过程
     * 1、先把real数据线画出来
     * 2、再把prediction数据线画出来
     * 3、或者两线一起画，但是real线走在prediction线前面
     */
    private static int itemID = 1;

    private static int flag = 0;

    public ArithmeticTrain getDataItem(ArithmeticTrainMapper arithmeticTrainMapper){
        if (flag == 0){
            itemID = 1;
            flag = 1;
        }
        ArithmeticTrain arithmeticTrain = arithmeticTrainMapper.selectById(itemID);

        itemID++;
        return arithmeticTrain;
    }

    public int setFlag(int x){
        flag = x;
        return flag;
    }

}
