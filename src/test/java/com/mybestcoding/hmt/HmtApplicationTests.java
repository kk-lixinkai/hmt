package com.mybestcoding.hmt;

import java.util.*;
import java.util.stream.Collectors;


import com.mybestcoding.hmt.constant.TsData;
import com.redislabs.redistimeseries.Aggregation;
import com.redislabs.redistimeseries.RedisTimeSeries;
import com.redislabs.redistimeseries.Value;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Slf4j
@SpringBootTest
class HmtApplicationTests {


    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    RedisTimeSeries rts;


    @Test
    void contextLoads() {
    }


    @Test
    public void printPool() {
        log.info(jedisConnectionFactory.getPoolConfig().toString());
    }

    @Test
    public void rtsTest() {
        Map<String, String> labels = new HashMap<>();
        labels.put("sensor_id", "2");
        labels.put("area_id", "32");
        rts.create("temperature:3:11", 60 * 10 * 10, labels);
        rts.add("temperature:3:11", 1548149181, 30.0);
        rts.add("temperature:3:11", 1548149191, 42.0);
    }

    @Test
    public void rtsRange() {
        Value[] values = rts.range("temperature:3:11", 1614746106, 1614747096, Aggregation.AVG, 10);
//        for (Value value : values) {
//            log.info("时间戳:{}, 平均值:{}", value.getTime(), value.getValue());
//        }

        readTsValues(values).forEach(s -> log.info("时间戳:{}, 平均值:{}", s.getTimestamp(), s.getValue()));
    }

    private List<TsData> readTsValues(Value[] values) {
        return Arrays.stream(values)
                .map(TsData::mapToTsData)
                .collect(Collectors.toList());
    }

}
