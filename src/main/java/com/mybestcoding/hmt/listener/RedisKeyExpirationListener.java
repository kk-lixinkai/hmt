package com.mybestcoding.hmt.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.charset.StandardCharsets;

/**
 * @author: lixinkai
 * @description: Redis 键过期监听器
 * @date: 2021/3/12 14:25
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 过期的键
        String expiredKey = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("过期的键为:{}", expiredKey);
    }
}
