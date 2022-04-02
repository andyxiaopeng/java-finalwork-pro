package com.example.demo.utiles.mqtt.objects;

import cn.hutool.json.JSONUtil;
import com.example.demo.manage.RedisDataManage;
import com.example.demo.manage.SpringUtil;
import com.example.demo.model.dto.MqttDataBean;
import com.example.demo.utiles.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Set;

@Slf4j
public class MessageListener implements IMqttMessageListener {

    /**
     * 处理消息
     * @param topic 主题
     * @param mqttMessage 消息
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        String jsonString = new String(mqttMessage.getPayload());
        log.info(String.format("MQTT: 订阅主题[%s]发来消息[%s]", topic, jsonString));

        /**处理消息
         * topic是由三级构成 ： 加氢站/设备名/属性名
         * 根据设备名和属性名来存入mqtt的store中
         */

        //        不能直接在回调方法中使用注解形式来获取对象和Service（如@Autowired、@Service），直接使用注解会报java.lang.NullException错误,然后断开MQTT连接。
        ApplicationContext context = SpringUtil.context;  //获取Spring容器
        RedisUtil redisUtil = context.getBean(RedisUtil.class);  //获取bean


        // 根据mqtt所传的json转换成mqttData的bean ==》  方便操作
        MqttDataBean mqttDataBean = JSONUtil.toBean(jsonString, MqttDataBean.class);

        // 把mqttDataBean的data部分取出来
        HashMap<String, String> data = mqttDataBean.getData();

        // 把mqttDataBean的设备id取出来
        String deviceId = mqttDataBean.getDeviceId();

        // 开始处理data部分
        RedisDataManage.init();// redis数据管理类初始化
        HashMap<String, Integer> hashMap = new HashMap();
        Set<String> keySet = data.keySet();
        for (String key : keySet) {
            String redisAttribute = new String(deviceId + key);
            RedisDataManage.insertRedisAttributeList(redisAttribute);
            boolean setBoolean = redisUtil.set(redisAttribute, data.get(key));
            System.out.println("key: " + key + "value: " + data.get(key) + " status: " + setBoolean);
        }

        System.out.println(hashMap);
    }

}
