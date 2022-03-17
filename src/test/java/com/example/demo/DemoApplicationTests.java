package com.example.demo;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.ChangeLogMapper;
import com.example.demo.model.entity.ChangeLog;
import com.example.demo.model.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utiles.RSAUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ChangeLogMapper changeLogMapper;

    @Test
    void insettest(){

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
