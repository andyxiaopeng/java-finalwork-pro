package com.example.demo.manage;

import cn.hutool.json.JSONUtil;
import com.example.demo.model.dto.MqttDataBean;
import com.example.demo.model.vo.Message;
import com.example.demo.utiles.websocket.manage.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WebSocketManage {
    @Autowired
    private WebSocket webSocket;

    public void sendMqttDataBeanToAllWebSocketClientMessage(MqttDataBean mqttDataBean){
        Date date = new Date();
        mqttDataBean.setTimeStamp(String.valueOf(date.getTime()));
        Message<Object> message = new Message();
        message.setData(mqttDataBean);
        message.initSuccessMessage();
        webSocket.sendAllMessage(JSONUtil.toJsonStr(message));
        return ;
    }

}
