package com.example.demo.service.impl;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.model.dto.LoginForm;
import com.example.demo.model.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.vo.Message;
import com.example.demo.service.UserService;
import com.example.demo.utiles.JWTUtils;
import com.example.demo.utiles.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ChangeLogServiceImpl changeLogServiceImpl;

    @Override
    public Message getPublicKey() {
        Message<HashMap> message = new Message();

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("hydrogen-Server",true);

        // publickey 需要用用 RSA生成
        RSAUtils rsaUtils = new RSAUtils();
        rsaUtils.iniRSA();
        objectObjectHashMap.put(rsaUtils.PUBLIC_KEY, rsaUtils.RSAkeyMap.get(rsaUtils.PUBLIC_KEY));

        message.setData(objectObjectHashMap);
        message.initSuccessMessage();
        return message;
    }

    @Override
    public Message login(LoginForm loginForm) {
        Message<HashMap> message = new Message();

        // RSA解码
        String param = loginForm.getParam();
        RSAUtils rsaUtils = new RSAUtils();
        String s = rsaUtils.privateKeyDecrypt(param);
        JSONUtil jsonUtil = new JSONUtil();
        JSONObject jsonObject = jsonUtil.parseObj(s);
        String account = String.valueOf(jsonObject.get("username"));
        String password = String.valueOf(jsonObject.get("password"));

        // 使用账号与数据库对比验证
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("account", account);
        List<User> users = userMapper.selectList(qw);
        if (users.size() == 0){
             message.invalidCodeMessage();
            return message;
        }
        for (User user : users) {

            // 验证密码是否正确
            boolean b = user.getPassword().equals(password);
            if (b) {
                // Token 需要用java包生成
                JWTUtils jwtUtils = new JWTUtils();
                String token = jwtUtils.getToken(account,password);

                // token 写入数据库
                user.setAccesstoken(token);
                userMapper.updateById(user);

                // token返回前端
                HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("accessToken",token);
                message.setData(objectObjectHashMap);
                message.initSuccessMessage();

                return message;
            }

        }
        message.invalidCodeMessage();
        return message;
    }

    @Override
    public Message getUserInfo(LoginForm loginForm) {
        String accessToken = loginForm.getAccessToken();

        Message<HashMap> message = new Message();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("accessToken",accessToken);

        // 凭借token拿 用户的信息，包括权限
        // 根据token查询数据库
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("accessToken", accessToken);
        List<User> users = userMapper.selectList(qw);
        if (users.size() == 0){
            return null;
        }



        for (User user : users) {
            objectObjectHashMap.put("username",user.getUsername());//用户名字


            changeLogServiceImpl.insertChangeLog(user.getUsername(),"login");//写登录log

            String permissions = user.getPermissions();//用户权限
            objectObjectHashMap.put("permissions",permissions.split(","));

            ArrayList<String> avatar = new ArrayList<>();//用户头像链接
            avatar.add("https://i.gtimg.cn/club/item/face/img/2/15922_100.gif");
            avatar.add("https://i.gtimg.cn/club/item/face/img/8/15918_100.gif");
            objectObjectHashMap.put("avatar","https://baomidou.com/img/logo.svg");

        }




        message.setData(objectObjectHashMap);
        message.initSuccessMessage();
        return message;
    }

    @Override
    public Message logout() {
        Message message = new Message();
        message.initSuccessMessage();
        return message;
    }
}
