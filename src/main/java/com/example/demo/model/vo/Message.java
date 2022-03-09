package com.example.demo.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@Data
public class Message<E> {


    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("totalCount")
    private Integer totalCount;
    @JsonProperty("data")
    private E data;
//    private HashMap<Object,Object> data;


    public void initSuccessEessage(){
        this.setCode(200);
        this.setMsg("success");
        this.setTotalCount(999);
    }

}
