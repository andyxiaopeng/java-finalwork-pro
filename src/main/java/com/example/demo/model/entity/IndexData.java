package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("indexdata")
public class IndexData {
    @TableId(value = "id")
    private Integer id;

    private String allvisitor;

    private String alldata;

    private String alluser;

    private String alldevice;

    private String alllog;

    private String alltraindata;

    private String alltestdata;
}
