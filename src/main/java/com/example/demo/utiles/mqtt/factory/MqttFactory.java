package com.example.demo.utiles.mqtt.factory;

import com.example.demo.utiles.mqtt.bean.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MqttFactory {

    private static MqttProperties configuration;

    private static String address = "tcp://116.62.115.28:1883";

    private static MqttClient client;

    /**
     *   获取客户端实例
     *   单例模式, 存在则返回, 不存在则初始化
     */
    public static MqttClient getInstance() {
        if (client == null) {
            init();
        }
        return client;
    }

    /**
     *   初始化客户端
     */
    public static void init() {
        configuration = new MqttProperties();
        try {
//            String address = "tcp://10.66.3.117:1883";
            client = new MqttClient(configuration.getAddress(), "java-client-" + System.currentTimeMillis());
//            client = new MqttClient(address, "client-" + System.currentTimeMillis());
            // MQTT配置对象
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置自动重连, 其它具体参数可以查看MqttConnectOptions
            options.setAutomaticReconnect(true);
            if (!client.isConnected()) {
                client.connect(options);
            }
        } catch (MqttException e) {
//            String address = "tcp://10.66.3.117:1883";
            log.error(String.format("MQTT: 连接消息服务器[%s]失败", configuration.getAddress()));
//            log.error(String.format("MQTT: 连接消息服务器[%s]失败", address));
        }
    }

}