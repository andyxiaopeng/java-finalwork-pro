package com.example.demo.manage.vo;

import com.example.demo.model.vo.Message;

public class MessageManager {
    public Message initSuccessEessage(Message message){
        message.setCode(200);
        message.setMsg("success");
        message.setTotalCount(999);

        return message;
    }
}
