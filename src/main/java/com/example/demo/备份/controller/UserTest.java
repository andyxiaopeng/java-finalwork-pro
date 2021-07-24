//package com.example.demo.备份.controller;
//
//
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "*",maxAge = 3600)
//@RequestMapping("user")
//public class UserTest {
//
//    @Resource
//    private UserMapper userMapper;
//
//    @RequestMapping("getalluser")
//    public Object getAllUser(){
////        http://127.0.0.1:8080/user/getuser
//        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
//
////        return JSON.toJSON(userList);
//        return userList;
//    }
//
//    @PostMapping("getuser")
////    private Message getUser(@RequestBody Map params){
//    private Message getUser(@RequestBody InParam inParam){
//        System.out.println(inParam);
//        System.out.println("userid: " + inParam.getUserid());
//        Message message = new Message();
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("admin-accessToken");
//
//        message.setMsg("success");
//        message.setCode(200);
//        message.setData(strings);
////        userMapper.selectById()
//        return message;
//    }
//
//}
