package com.example.demo.manage.returnmanage;

import com.example.demo.entity.returnpojo.Message;

public class MessageMnage {
    public Message initSuccessEessage(Message message){
        message.setCode(200);
        message.setMsg("success");
        message.setTotalCount(999);

        return message;
    }
}
