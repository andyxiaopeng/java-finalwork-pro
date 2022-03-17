package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("changelog")
public class ChangeLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String content;

    private String timestamp;
}
