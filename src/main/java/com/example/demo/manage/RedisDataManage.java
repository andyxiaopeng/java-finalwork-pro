package com.example.demo.manage;

import com.example.demo.model.dto.RedisDataBean;
import com.example.demo.utiles.redis.util.RedisUtil;
import lombok.Data;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RedisDataManage {

    public static Set<String> redisAttributeList;
    private static RedisDataBean redisDataBean;

    /**
     * 初始化redis管理类
     * @return
     */
    public static boolean init(){
        if (redisDataBean == null) {
            redisDataBean = new RedisDataBean();
            redisDataBean.setData(new HashMap<>());
        }
        if (redisAttributeList == null){
            redisAttributeList = new HashSet<>();
        }
        return true;
    }

    /**
     * 添加redis的key到静态公共列表中
     * @param value
     * @return
     */
    public static boolean insertRedisAttributeList(String value){
        return redisAttributeList.add(value);
    }

    /**
     * 根据key的公共列表获取redis里面的数值，返回redisData的bean
     * @param redisUtil
     * @return
     */
    public static RedisDataBean getAllRedisData(RedisUtil redisUtil){
        HashMap<String, Integer> data = redisDataBean.getData();

        for (String key : redisAttributeList){
            Object value = redisUtil.get(key);
            data.put(key, Integer.parseInt(value.toString()));
        }
        redisDataBean.setData(data);
        return redisDataBean;
    }

}
