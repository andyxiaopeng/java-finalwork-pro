package com.example.demo.manage;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

/**
 * 不能直接在回调方法中使用注解形式来获取对象和Service（如@Autowired、@Service），直接使用注解会报java.lang.NullException错误,然后断开MQTT连接。
 *  ApplicationContext context = SpringUtil.context;  //获取Spring容器
 *  RedisUtil redisUtil = context.getBean(RedisUtil.class);  //获取bean
 */
@Component
public class SpringUtil extends ApplicationObjectSupport {
    public static ApplicationContext context;

    public static Object getBean(String serviceName){
        return context.getBean(serviceName);
    }

    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        super.initApplicationContext(context);
        SpringUtil.context = context;
    }
}
