package com.example.demo.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("arithmeticshow")
public class ArithmeticShow {
    private Integer id;

    private String realvalue;

    private String prediction;

    private String threshold;

    private String status;
}
