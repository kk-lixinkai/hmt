package com.mybestcoding.hmt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.mybestcoding.hmt.mapper"})
public class MyBatisConfig {
}
