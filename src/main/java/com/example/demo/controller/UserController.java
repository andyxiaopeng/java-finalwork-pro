package com.example.demo.controller;


import com.example.demo.entity.enterpojo.LoginForm;
import com.example.demo.entity.returnpojo.Message;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyh
 * @since 2021-07-24
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    /**
     * 实现登录加密
     * @return
     */
    @RequestMapping("publicKey")
    private Message getPublicKey(){
        Message<HashMap> message = new Message();

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("mockServer",true);
        objectObjectHashMap.put("publicKey","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBT2vr+dhZElF73FJ6xiP181txKWUSNLPQQlid6DUJhGAOZblluafIdLmnUyKE8mMHhT3R+Ib3ssZcJku6Hn72yHYj/qPkCGFv0eFo7G+GJfDIUeDyalBN0QsuiE/XzPHJBuJDfRArOiWvH0BXOv5kpeXSXM8yTt5Na1jAYSiQ/wIDAQAB");

        message.setData(objectObjectHashMap);

        message.initSuccessEessage();
        return message;
    }

    /**
     * 实现登录功能
     * @param loginForm
     * @return
     */
    @RequestMapping("login")
    public Message login(@RequestBody LoginForm loginForm){

        Message<HashMap> message = new Message();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("accessToken","admin-accessToken");
        message.setData(objectObjectHashMap);

        System.out.println(message.toString());
        message.initSuccessEessage();
        return message;
    }

    /**
     * 实现获取账号信息功能
     * @param loginForm
     * @return
     */
    @RequestMapping("userInfo")
    public Message userInfo(@RequestBody LoginForm loginForm){

        Message<HashMap> message = new Message();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("accessToken","admin-accessToken");

        objectObjectHashMap.put("username","test");

        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("admin");
        permissions.add("editor");
        objectObjectHashMap.put("permissions",permissions);

        ArrayList<String> avatar = new ArrayList<>();
        avatar.add("https://i.gtimg.cn/club/item/face/img/2/15922_100.gif");
        avatar.add("https://i.gtimg.cn/club/item/face/img/8/15918_100.gif");
        objectObjectHashMap.put("avatar","https://i.gtimg.cn/club/item/face/img/2/15922_100.gif");
        message.setData(objectObjectHashMap);

        message.initSuccessEessage();
        return message;
    }

    /**
     * 实现登出功能
     * @return
     */
    @RequestMapping("logout")
    private Message logout(){
        Message message = new Message();
        message.initSuccessEessage();
        return message;
    }

}

