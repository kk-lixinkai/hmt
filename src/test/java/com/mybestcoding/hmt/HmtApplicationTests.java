package com.mybestcoding.hmt;

import java.util.Date;
import java.util.UUID;


import com.mybestcoding.hmt.mapper.RoleMapper;
import com.mybestcoding.hmt.mapper.UserMapper;
import com.mybestcoding.hmt.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class HmtApplicationTests {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void mybatisTest() {
        log.info(roleMapper.selectByPrimaryKey(1).toString());
    }

    @Test
    public void userTest() {
        String s = UUID.randomUUID().toString();

        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("zhangsan");
        user.setTel("13415626441");
        user.setEmail("kk-lixinkai@qq.com");
        user.setSalt(s.substring(0, s.indexOf("-")));
        user.setUserKey(s.substring(s.lastIndexOf("-") + 1));
        user.setCreatedTime(new Date());
        user.setLastLoginTime(new Date());
        user.setUpdatedTime(new Date());


        userMapper.insert(user);

        log.info(user.toString());
    }

    @Test
    public void selectOne() {
        User user = userMapper.selectByPrimaryKey(2);
        log.info(user.toString());
    }

}
