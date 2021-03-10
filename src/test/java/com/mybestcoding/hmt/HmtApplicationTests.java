package com.mybestcoding.hmt;

import java.util.*;
import java.util.stream.Collectors;


import com.mybestcoding.hmt.constant.NodeType;
import com.mybestcoding.hmt.constant.Status;
import com.mybestcoding.hmt.constant.TsData;
import com.mybestcoding.hmt.mapper.*;
import com.mybestcoding.hmt.model.*;
import com.mybestcoding.hmt.util.CommonUtil;
import com.redislabs.redistimeseries.Value;
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

    @Autowired
    WareHouseMapper wareHouseMapper;

    @Autowired
    TerminalDeviceMapper terminalDeviceMapper;

    @Autowired
    NodeMapper nodeMapper;


    @Test
    void contextLoads() {
    }


    @Test
    public void printPool() {
//        Role admin = (Role) new Role().setRole("ADMIN").setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
//        int i = roleMapper.insertSelective(admin);
//        log.info("插入的结果：{}", i);

        Role creator = (Role) new Role().setRole("CREATOR").setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        Role editor = (Role) new Role().setRole("EDITOR").setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        Role user = (Role) new Role().setRole("USER").setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        roleMapper.insertSelective(creator);
        log.info("CREATOR 的信息 {}", creator);
        roleMapper.insertSelective(editor);
        log.info("CREATOR 的信息 {}", editor);
        roleMapper.insertSelective(user);
        log.info("CREATOR 的信息 {}", user);
    }


    @Test
    public void insertUser() {
        String user1 = CommonUtil.getSalt();
        String user1p = "lixinkai";
        String password1 = CommonUtil.encryption(user1, user1);
        User lixinkai = (User) new User().setNickname("lixinkai").setPassword(password1).setSalt(user1).setEmail("kk-lixinkai@qq.com").setTel("13553357044").setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        userMapper.insertSelective(lixinkai);
        log.info("插入的用户信息: {}", lixinkai);
    }


    private List<TsData> readTsValues(Value[] values) {
        return Arrays.stream(values)
                .map(TsData::mapToTsData)
                .collect(Collectors.toList());
    }


    public void insertMenus() {
    }

    @Test
    public void initW() {
        String longitude = "113.673207";
        String latitude = "24.77654";
        WareHouse wareHouse = new WareHouse().setLongitude(longitude).setLatitude(latitude).setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        wareHouseMapper.insertSelective(wareHouse);
        log.info("初始化仓库的信息:{}", wareHouse);
        TerminalDevice terminalDevice = new TerminalDevice().setStatus(Status.OK.getStatus()).setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        terminalDeviceMapper.insertSelective(terminalDevice);
        log.info("初始化的终端信息:{}", terminalDevice);
        Node temperature = new Node().setStatus(Status.OK.getStatus()).setType(NodeType.TEMPERATURE.getType()).setWid(wareHouse.getId()).setTid(terminalDevice.getId()).setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        nodeMapper.insertSelective(temperature);
        log.info("初始化的温度节点: {}", temperature);
        Node humidity = new Node().setStatus(Status.OK.getStatus()).setType(NodeType.HUMIDITY.getType()).setWid(wareHouse.getId()).setTid(terminalDevice.getId()).setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        nodeMapper.insertSelective(humidity);
        log.info("初始化的湿度节点: {}", humidity);
        Node lightintensity = new Node().setStatus(Status.OK.getStatus()).setType(NodeType.LIGHTINTENSITY.getType()).setWid(wareHouse.getId()).setTid(terminalDevice.getId()).setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        nodeMapper.insertSelective(lightintensity);
        log.info("初始化的光照强度: {}", lightintensity);
        Node flamealarm = new Node().setStatus(Status.OK.getStatus()).setType(NodeType.FLAMEALARM.getType()).setWid(wareHouse.getId()).setTid(terminalDevice.getId()).setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        nodeMapper.insertSelective(flamealarm);
        log.info("初始化的火焰报警节点:{}", flamealarm);

    }
}
