package com.example.demo.manage;

import com.example.demo.model.dto.RedisDataBean;
import com.example.demo.utiles.redis.util.RedisUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class RedisDataManage {

    public static Set<String> redisKeys;
    private static RedisDataBean redisDataBean;

    /**
     * 初始化redis管理类
     * @return
     */
    public boolean init(){
        if (redisDataBean == null) {
            redisDataBean = new RedisDataBean();
            redisDataBean.setData(new HashMap<>());
        }
        if (redisKeys == null){
            redisKeys = new HashSet<>();

            redisKeys.add("compressor-A");
            redisKeys.add("compressor-B");
        }
        return true;
    }

    /**
     * 添加redis的key到静态公共列表中
     * @param value
     * @return
     */
    public boolean insertRedisAttributeList(String value){
        return redisKeys.add(value);
    }

    /**
     * 根据key的公共列表获取redis里面的数值，返回redisData的bean
     * @param redisUtil
     * @return
     */
    public RedisDataBean getAllRedisData(RedisUtil redisUtil){
        HashMap<String, Integer> data = redisDataBean.getData();

        for (String key : redisKeys){
            Object value = redisUtil.get(key);
            data.put(key, Integer.parseInt(value.toString()));
        }
        redisDataBean.setData(data);
        return redisDataBean;
    }

    public HashMap<String,List> getListAllRedisData(RedisUtil redisUtil){
        HashMap<String,List> map = new HashMap<>();

        for (String key : redisKeys) {
            List<Object> lGet = redisUtil.lGet(key, 0, -1);
            map.put(key,lGet);
        }
        return map;
    }

    /**
     * 重置redis中某个list的size
     * @param redisUtil
     * @return
     */
    public long resetRedisSize(RedisUtil redisUtil, String key, int size){
        long listSize = redisUtil.lGetListSize(key);

        long l = listSize - size;
        if(l>0){
            List<Object> lGet = redisUtil.lGet(key, l, listSize -1);
            redisUtil.del(key);
            redisUtil.lSet(key,lGet,3600);
        }
        return redisUtil.lGetListSize(key);
    }

}
