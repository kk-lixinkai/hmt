package com.mybestcoding.hmt.broker;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

/**
 * @author: lixinkai
 * @description:
 * @date: 2021/3/8 12:34
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Component
@MessagingGateway(defaultRequestChannel = "mqttInputChannel")
public interface MqttGateway {
    void sendToMqtt(String message);
}
