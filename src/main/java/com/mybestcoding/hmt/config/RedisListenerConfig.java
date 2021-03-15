package com.mybestcoding.hmt.config;

import com.mybestcoding.hmt.listener.RedisKeyExpirationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author: lixinkai
 * @description: Redis 监听器配置
 * @date: 2021/3/12 14:29
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Configuration
public class RedisListenerConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    public RedisListenerConfig(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }

    @Bean
    public RedisKeyExpirationListener listener() {
        return new RedisKeyExpirationListener(redisMessageListenerContainer());
    }
}
