package com.example.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Notice implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private boolean closable;

    private String type;
}
