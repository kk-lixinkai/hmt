package com.mybestcoding.hmt.controller;

import com.mybestcoding.hmt.annotation.LogOperation;
import com.mybestcoding.hmt.broker.MqttBroker;
import com.mybestcoding.hmt.broker.MqttGateway;
import com.mybestcoding.hmt.constant.LogOperationType;
import com.mybestcoding.hmt.constant.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lixinkai
 * @description: 测试接口
 * @date: 2021/3/7 17:35
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@RestController
@Api("测试")
public class TestController {

    @Autowired
    MqttGateway gateway;


    @ApiOperation(value = "发布主题", notes = "测试发布主题")
    @GetMapping(value = "/publishTopic")
    public ResponseBody publishTopic() {
        gateway.sendToMqtt("hello, SpringBoot");
        return ResponseBody.success("");
    }


    @LogOperation(module = "测试", type = LogOperationType.ADDED, desc = "测试接口")
    @GetMapping("/test")
    public ResponseBody test() {
        return ResponseBody.success("");
    }
}
