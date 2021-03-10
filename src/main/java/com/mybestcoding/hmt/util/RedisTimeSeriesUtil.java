package com.mybestcoding.hmt.util;

import com.mybestcoding.hmt.constant.TsData;
import com.mybestcoding.hmt.constant.TsMData;
import com.redislabs.redistimeseries.Aggregation;
import com.redislabs.redistimeseries.Range;
import com.redislabs.redistimeseries.RedisTimeSeries;
import com.redislabs.redistimeseries.Value;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: lixinkai
 * @description: Redis 时间序列数据工具
 * @date: 2021/3/3 8:55
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Slf4j
@Component
public class RedisTimeSeriesUtil {

    @Autowired
    private RedisTimeSeries redisTimeSeries;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 创建 TS 数据集，默认保存时间为 10 分钟
     *
     * @param key    键
     * @param labels 数据标签
     * @return true 创建成功 false 创建失败
     */
    public boolean tsCreate(String key, Map<String, String> labels) {
        boolean result = redisTimeSeries.create(key, 10 * 60, labels);
        return result;
    }

    /**
     * 创建一个数据集
     *
     * @param key    键
     * @param time   过期时间 单位 秒
     * @param labels 数据标签
     * @return
     */
    public boolean tsCreate(String key, long time, Map<String, String> labels) {
        boolean result = redisTimeSeries.create(key, time, labels);
        return result;
    }


    /**
     * 获取数据，默认是最新的数据
     *
     * @param key 键
     * @return 值
     */
    public TsData tsGet(String key) {
        Value value = redisTimeSeries.get(key);
        TsData tsData = TsData.mapToTsData(value);
        return tsData;
    }


    /**
     * 添加TS数据
     *
     * @param key       键
     * @param timestamp 时间戳
     * @param value     值
     * @return true 成功 false 失败
     */
    public boolean tsAdd(String key, long timestamp, double value) {
        // 返回一个时间戳
        long time = redisTimeSeries.add(key, timestamp, value);
        return time > 0;
    }

    /**
     * 添加TS数据，并设置保留时间
     *
     * @param key       键
     * @param timestamp 时间戳
     * @param value     值
     * @param expected  保留时间
     * @return
     */
    public boolean tsAdd(String key, long timestamp, double value, long expected) {
        long time = redisTimeSeries.add(key, timestamp, value, expected);
        return time > 0;
    }


    /**
     * TS 数据的聚合计算
     *
     * @param key            键
     * @param startTimestamp 起始时间
     * @param endTimestamp   结束时间
     * @param cal            计算方式 {@link Aggregation}
     * @param timeBucket     每次聚合计算的时间窗口大小
     * @return
     */
    public List<TsData> tsRange(String key, long startTimestamp, long endTimestamp, Aggregation cal, long timeBucket) {
        List<TsData> tsDataList = null;
        switch (cal) {
            case AVG:
                Value[] avgResult = redisTimeSeries.range(key, startTimestamp, endTimestamp, Aggregation.AVG, timeBucket);
                tsDataList = readTsValues(avgResult);
                break;
            case SUM:
                Value[] sumResult = redisTimeSeries.range(key, startTimestamp, endTimestamp, Aggregation.SUM, timeBucket);
                tsDataList = readTsValues(sumResult);
                break;
            case MAX:
                Value[] maxResult = redisTimeSeries.range(key, startTimestamp, endTimestamp, Aggregation.MAX, timeBucket);
                tsDataList = readTsValues(maxResult);
                break;
            case MIN:
                Value[] minResult = redisTimeSeries.range(key, startTimestamp, endTimestamp, Aggregation.MIN, timeBucket);
                tsDataList = readTsValues(minResult);
                break;
            case COUNT:
                Value[] countResult = redisTimeSeries.range(key, startTimestamp, endTimestamp, Aggregation.COUNT, timeBucket);
                tsDataList = readTsValues(countResult);
            default:
                log.error("不支持的聚合运算");
                break;
        }
        return tsDataList;
    }


    /**
     * 条件过滤 和 聚合计算
     *
     * @param startTimestamp 起始的时间戳
     * @param endTimestamp   结束的时间戳
     * @param cal            聚合计算方式
     * @param timeBucket     时间窗口
     * @param filter         过滤条件 例如: "sensor_id = 32" 支持多个条件组合
     * @return 操作结果
     */
    public List<TsMData> tsMRange(long startTimestamp, long endTimestamp, Aggregation cal, long timeBucket, String... filter) {
        List<TsMData> tsMDataList = null;
        switch (cal) {
            case AVG:
                Range[] avgMData = redisTimeSeries.mrange(startTimestamp, endTimestamp, Aggregation.AVG, timeBucket, filter);
                tsMDataList = readTsMData(avgMData);
                break;
            case SUM:
                Range[] sumMData = redisTimeSeries.mrange(startTimestamp, endTimestamp, Aggregation.SUM, timeBucket, filter);
                tsMDataList = readTsMData(sumMData);
                break;
            case MAX:
                Range[] maxMData = redisTimeSeries.mrange(startTimestamp, endTimestamp, Aggregation.MAX, timeBucket, filter);
                tsMDataList = readTsMData(maxMData);
                break;
            case MIN:
                Range[] minMData = redisTimeSeries.mrange(startTimestamp, endTimestamp, Aggregation.MIN, timeBucket, filter);
                tsMDataList = readTsMData(minMData);
                break;
            case COUNT:
                Range[] countMData = redisTimeSeries.mrange(startTimestamp, endTimestamp, Aggregation.COUNT, timeBucket, filter);
                tsMDataList = readTsMData(countMData);
                break;
            default:
                log.error("不支持该操作");
                break;
        }
        return tsMDataList;
    }


    /**
     * 删除 TS 中的数据集
     *
     * @param key 键
     * @return true 成功 false 失败
     */
    public boolean tsDelKey(String key) {
        Boolean result = redisTemplate.delete(key);
        return result;
    }


    /**
     * 获取 range操作返回的数据
     *
     * @param values redis 中的ts数据
     * @return
     */
    private List<TsData> readTsValues(Value[] values) {
        return Arrays.stream(values)
                .map(TsData::mapToTsData)
                .collect(Collectors.toList());
    }

    /**
     * 读取 mrange从操作返回的数据
     *
     * @param ranges
     * @return
     */
    private List<TsMData> readTsMData(Range[] ranges) {
        return Arrays.stream(ranges)
                .map(TsMData::mResultMapToTsMData)
                .collect(Collectors.toList());
    }
}
