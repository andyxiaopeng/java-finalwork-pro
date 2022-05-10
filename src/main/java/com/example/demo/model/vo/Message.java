package com.example.demo.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@Data
@Component
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


    public void initSuccessMessage(){
        this.setCode(200);
        this.setMsg("success");
    }

    public void invalidCodeMessage(){
        this.setCode(500);
        this.setMsg("帐户或密码不正确。");
        this.data = null;
    }

    public void noPermissionCodeMessage(){
        this.setCode(401);
        this.setMsg("无权限");
        this.data = null;
    }

    public void initErrorMessage(){
        this.setCode(500);
        this.setMsg("Error");
        this.data = null;
    }

}
