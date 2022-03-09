package com.example.demo;

import com.example.demo.model.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    void contextLoads() {
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
