package com.mybestcoding.hmt.service.impl;

import com.mybestcoding.hmt.constant.TsData;
import com.mybestcoding.hmt.constant.TsMData;
import com.mybestcoding.hmt.service.RedisTimeSeriesService;
import com.mybestcoding.hmt.util.CommonUtil;
import com.mybestcoding.hmt.util.RedisTimeSeriesUtil;
import com.redislabs.redistimeseries.Aggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: lixinkai
 * @description: 时间序列 服务
 * @date: 2021/3/8 21:00
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class RedisTimeSeriesServiceImpl implements RedisTimeSeriesService {

    private final RedisTimeSeriesUtil redisTimeSeriesUtil;

    @Autowired
    public RedisTimeSeriesServiceImpl(RedisTimeSeriesUtil redisTimeSeriesUtil) {
        this.redisTimeSeriesUtil = redisTimeSeriesUtil;
    }

    @Override
    public boolean tsCreate(String key, Map<String, String> labels) {
        boolean result = redisTimeSeriesUtil.tsCreate(key, labels);
        return result;
    }

    @Override
    public boolean tsAdd(String key, TsData tsData) {
        boolean result = redisTimeSeriesUtil.tsAdd(key, tsData.getTimestamp(), tsData.getValue());
        return result;
    }

    @Override
    public TsData getLastData(String key) {
        return redisTimeSeriesUtil.tsGet(key);
    }

    @Override
    public List<TsData> getRangeData(String key, String startTime, String endTime, Aggregation cal, long timeBucket) {
        long sTimestamp = CommonUtil.DateToTimestamp(startTime);
        long eTimestamp = CommonUtil.DateToTimestamp(endTime);
        List<TsData> tsData = redisTimeSeriesUtil.tsRange(key, sTimestamp, eTimestamp, cal, timeBucket);
        return tsData;
    }

    @Override
    public List<TsMData> getDataByFilters(String startTime, String endTime, Aggregation cal, long timeBucket, String... filters) {
        long sTimestamp = CommonUtil.DateToTimestamp(startTime);
        long eTimestamp = CommonUtil.DateToTimestamp(endTime);
        List<TsMData> tsMDataList = redisTimeSeriesUtil.tsMRange(sTimestamp, eTimestamp, cal, timeBucket, filters);
        return tsMDataList;
    }
}
