package com.mybestcoding.hmt.config;

import com.redislabs.redistimeseries.RedisTimeSeries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPool;


/**
 * @author: lixinkai
 * @description: Redis 时间序列模块配置
 * @date: 2021/3/2 17:34
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Configuration
public class RedisTimeSeriesConfig {
    @Value("${redistimeseries.host}")
    private String host;
    @Value("${redistimeseries.port}")
    private int port = 6379;
    @Value("${redistimeseries.password}")
    private String password = "lixinkai";


//    @Bean
//    public RedisTimeSeries getRedisTimeSeries(@Autowired JedisConnectionFactory jedisConnectionFactory) {
//        // 垃圾源码.........
//        return new RedisTimeSeries(new JedisPool(jedisConnectionFactory.getPoolConfig(), jedisConnectionFactory.getHostName(), jedisConnectionFactory.getPort(), jedisConnectionFactory.getTimeout(), jedisConnectionFactory.getPassword()));
//    }

    @Bean
    public RedisTimeSeries getRedisTimeSeries() {
        // 垃圾源码.........
        return new RedisTimeSeries(host, port, 1000, 8, password);
    }
}
