package com.mybestcoding.hmt;

import com.mybestcoding.hmt.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@EnableCaching
@EnableAspectJAutoProxy
@SpringBootApplication
public class HmtApplication implements CommandLineRunner {

    @Value("${server.port}")
    String port;


    public static void main(String[] args) {
        SpringApplication.run(HmtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Swagger 页面为 {}", "http://localhost:" + port + "/swagger-ui.html#/");
    }
}
