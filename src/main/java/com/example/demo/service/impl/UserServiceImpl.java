package com.example.demo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.model.dto.LoginForm;
import com.example.demo.model.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.vo.Message;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyh
 * @since 2021-07-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Message getPublicKey() {
        Message<HashMap> message = new Message();

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("hydrogen-Server",true);

        // publickey 需要用用 RSA生成
        objectObjectHashMap.put("publicKey","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBT2vr+dhZElF73FJ6xiP181txKWUSNLPQQlid6DUJhGAOZblluafIdLmnUyKE8mMHhT3R+Ib3ssZcJku6Hn72yHYj/qPkCGFv0eFo7G+GJfDIUeDyalBN0QsuiE/XzPHJBuJDfRArOiWvH0BXOv5kpeXSXM8yTt5Na1jAYSiQ/wIDAQAB");

        message.setData(objectObjectHashMap);

        message.initSuccessEessage();


        return message;
    }

    @Override
    public Message login(LoginForm loginForm) {

        Message<HashMap> message = new Message();

        System.out.println(loginForm.toString());

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();


        // Token 需要用java包生成
        objectObjectHashMap.put("accessToken","admin-accessToken");
        message.setData(objectObjectHashMap);

//        System.out.println(message.toString());
        message.initSuccessEessage();

        return message;
    }

    @Override
    public Message getUserInfo(LoginForm loginForm) {

        Message<HashMap> message = new Message();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("accessToken","admin-accessToken");

        // 凭借token拿 用户的信息，包括权限

        objectObjectHashMap.put("username","test");//用户名字
        User user = new User();

        ArrayList<String> permissions = new ArrayList<>();//用户权限
        permissions.add("admin");
        permissions.add("editor");
        objectObjectHashMap.put("permissions",permissions);

        ArrayList<String> avatar = new ArrayList<>();//用户头像链接
        avatar.add("https://i.gtimg.cn/club/item/face/img/2/15922_100.gif");
        avatar.add("https://i.gtimg.cn/club/item/face/img/8/15918_100.gif");
        objectObjectHashMap.put("avatar","https://i.gtimg.cn/club/item/face/img/2/15922_100.gif");
        message.setData(objectObjectHashMap);

        message.initSuccessEessage();
        return message;
    }

    @Override
    public Message logout() {
        Message message = new Message();
        message.initSuccessEessage();
        return message;
    }
}
