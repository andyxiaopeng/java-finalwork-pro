package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.*;
import com.example.demo.model.entity.*;
import com.example.demo.model.vo.Message;
import com.example.demo.service.IndexPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexPaperImpl extends ServiceImpl<IndexPaperMapper, IndexData> implements IndexPaperService {

    @Autowired(required = false)
    private IndexPaperMapper indexPaperMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ArithmeticShowMapper arithmeticShowMapper;

    @Autowired(required = false)
    private ArithmeticTrainMapper arithmeticTrainMapper;

    @Autowired(required = false)
    private ChangeLogMapper changeLogMapper;

    @Override
    public Message getList() {
        Message message = new Message();

        IndexData indexData = indexPaperMapper.selectById(1);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Integer count = userMapper.selectCount(wrapper);


        indexData.setAlluser(String.valueOf(count));


        // 统计各表的数据
        QueryWrapper<ArithmeticShow> wrapper1 = new QueryWrapper<>();
        Integer count1 = arithmeticShowMapper.selectCount(wrapper1);
        indexData.setAlltestdata(String.valueOf(count1));

        QueryWrapper<ArithmeticTrain> wrapper2 = new QueryWrapper<>();
        Integer count2 = arithmeticTrainMapper.selectCount(wrapper2);
        indexData.setAlltraindata(String.valueOf(count2));

        QueryWrapper<ChangeLog> wrapper3 = new QueryWrapper<>();
        Integer count3 = changeLogMapper.selectCount(wrapper3);
        indexData.setAlllog(String.valueOf(count3));


        indexPaperMapper.updateById(indexData);
        indexData = indexPaperMapper.selectById(1);
        message.setData(indexData);
        message.initSuccessMessage();
        return message;
    }
}
