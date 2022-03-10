package com.example.demo.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.LoginForm;
import com.example.demo.model.dto.UserManageForm;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.Message;
import com.example.demo.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManageServiceImpl extends ServiceImpl<UserMapper, User> implements UserManageService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public Message getList(UserManageForm userManageForm) {

        System.out.println(userManageForm);
        //获取前台发送过来的数据
        Integer pageNo = Integer.valueOf(userManageForm.getPageNo());
        Integer pageSize = Integer.valueOf(userManageForm.getPageSize());
        Page<User> page = new Page<>(pageNo, pageSize);

//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        User user = new User();
//        user.setId(1);
//        wrapper.setEntity(user);
//        Page<User> userPage = this.page(page, wrapper);

        Page<User> userPage = this.page(page, null);
        Message message = new Message();

        message.setTotalCount(Math.toIntExact(userPage.getTotal()));
        List<User> records = userPage.getRecords();
        message.setData(records);

        message.initSuccessMessage();
        return message;
    }

    @Override
    public Message doEdit(LoginForm loginForm) {
        System.out.println(loginForm);
        String account = loginForm.getUsername();

        User user = new User();
        user.setAccount(account);
        user.setUsername(account);
        user.setPassword(loginForm.getPassword());
        user.setEmail(loginForm.getEmail());
        String join = String.join(",", loginForm.getPermissions());
        user.setPermissions(join);
        user.setAccesstoken("ini—Token-");

        // 查询数据库是否有同名账号
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("account",account);
        List<User> users = userMapper.selectList(qw);
        if (users.size() != 0){
            return null;
        }

        // 创建时间
        DateUtil dateUtil = new DateUtil();
        String now = dateUtil.now();
        user.setCreattime(now);

        // 写入数据库
        int insert = userMapper.insert(user);

        Message message = new Message();
        if (insert > 0) {
            message.initSuccessMessage();
        }
        return message;
    }

    @Override
    public Message doDelete() {
        Message message = new Message();
        return null;
    }
}


