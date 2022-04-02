package com.example.demo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.manage.RedisDataManage;
import com.example.demo.mapper.ChangeLogMapper;
import com.example.demo.model.dto.RedisDataBean;
import com.example.demo.model.entity.ChangeLog;
import com.example.demo.model.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utiles.RSAUtils;
import com.example.demo.utiles.mqtt.objects.MessageListener;
import com.example.demo.utiles.mqtt.util.MqttUtil;
import com.example.demo.utiles.redis.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private RedisUtil redisUtil;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ChangeLogMapper changeLogMapper;

    @Autowired(required = false)
    private MessageListener messageListener;

    @Test
    void mqttLiten(){
        MqttUtil.subscribe("test01", messageListener);
    }
    @Test
    void insetRedis(){
//        String key = "id";
//        String value = "852";

        String key = "device001";

        HashMap<String, Integer> values = new HashMap<>();
        values.put("agp",266);
        values.put("temp",566);

        HashMap<String,HashMap<String, Integer>> data = new HashMap<>();
        data.put(key,values);

        redisUtil.set("data",data);
    }
    @Test
    void getRedis(){
        String key = "data";
        Object o = redisUtil.get(key);
        System.out.println(o);
    }

    @Test
    void updateRedis(){
        String key = "data";
        String redisStr = redisUtil.get(key).toString();

        System.out.println(redisStr);

        RedisDataBean redisDataBean = JSONObject.parseObject(redisStr, RedisDataBean.class);
        System.out.println(redisDataBean);

//        RedisDataBean redisDataBean = JSONUtil.toBean(o.toString(), RedisDataBean.class);
//
//        HashMap<String, HashMap<String, Integer>> data = redisDataBean.getData();
//        HashMap<String, Integer> device001 = data.get("device001");
//
//        System.out.println("data"+data);
//        System.out.println();
//        System.out.println("device001"+device001);
    }

    @Test
    void testJavaRedis(){
        Set<String> aa = new HashSet<>();

        boolean addBoolean = aa.add("123");
        System.out.println(addBoolean);


        addBoolean = aa.add("3");
        System.out.println(addBoolean);

        addBoolean = aa.add("123");
        System.out.println(addBoolean);

        for (String s : aa){
            System.out.println(s);
        }

    }

    @Test
    void testRedisDataBean(){
        RedisDataBean redisDataBean = new RedisDataBean();


        HashMap<String, Integer> data = new HashMap<>();
        data.put("key1",123);
        System.out.println(data);

        data.put("key2",123);
        System.out.println(data);

        data.put("key1",456);
        System.out.println(data);


    }

    @Test
    void testRedisDataManage(){
        RedisDataManage.init();
        String key1 = "device-01-t";
        String key2 = "device-01-p";

        RedisDataManage.insertRedisAttributeList(key1);
        RedisDataManage.insertRedisAttributeList(key2);

        Set<String> redisAttributeList = RedisDataManage.redisAttributeList;
        for (String key : redisAttributeList) {
            redisUtil.set(key,666);
        }

        RedisDataBean allRedisData = RedisDataManage.getAllRedisData(redisUtil);

        System.out.println(allRedisData);
    }

    @Test
    void testRedisDataManageGet(){
        RedisDataManage.init();

        Set<String> redisAttributeList = RedisDataManage.redisAttributeList;
        for (String key : redisAttributeList) {
            redisUtil.set(key,666);
        }

        RedisDataBean allRedisData = RedisDataManage.getAllRedisData(redisUtil);

        System.out.println(allRedisData);
    }

    @Test
    void getUserList() {
        // 创建文本
        String content = "test";
        // 创建时间
        DateUtil dateUtil = new DateUtil();
        String timestamp = dateUtil.now();

        ChangeLog changeLog = new ChangeLog();
        changeLog.setContent(content);
        changeLog.setTimestamp(timestamp);

        System.out.println(changeLog);

        // 写入数据库
        changeLogMapper.insert(changeLog);
    }

    @Test
    void userSlectTest() {
        String account = "admin";

        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("account", account);
        List<User> users = userMapper.selectList(qw);
        if (users.size() == 0){
            System.out.println("meiyou");
        }

        for (User user : users) {
            System.out.println("\n\n");
            System.out.printf(user.getPassword());
            System.out.println("\n\n");

            boolean b = user.getPassword().equals("123456");
            if (b) {
                System.out.println("\n\n");
                System.out.printf("taaaaaaaaaaaaaarue");
                System.out.println("\n\n");



            }

        }
//        System.out.printf(users.toString());
    }

    @Test
    void RSAtests() {
        RSAUtils rsaUtils = new RSAUtils();
        rsaUtils.iniRSA();
        System.out.printf((String) rsaUtils.RSAkeyMap.get("publicKey"));
    }

    @Test
    private  void select(){
        System.out.println(("----- selectAll method test ------"));
        //参数是一个Wrapper，条件结构器，这里先不用 填null
        //查询所有的用户
        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
//        userList.forEach(System.out::println);

        for (User user : userList){
            System.out.println(user.getId() + " 名字：" + user.getUsername());
        }

        User user = userMapper.selectById("2");
        System.out.println(user.getId() + user.getUsername());
    }


    @Test
    public void testInsert() {
        System.out.println(("----- selectAll method test ------"));
        User user = new User();
//        user.setName=("shuishui");
        user.setUsername("shuishui");
        user.setPhone("12");
        user.setAccesstoken("12434141@qq.com");

        userMapper.insert(user);
    }

}
