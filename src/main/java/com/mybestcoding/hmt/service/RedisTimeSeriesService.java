package com.mybestcoding.hmt.service;

import com.mybestcoding.hmt.constant.TsData;
import com.mybestcoding.hmt.constant.TsMData;
import com.redislabs.redistimeseries.Aggregation;

import java.util.List;
import java.util.Map;

public interface RedisTimeSeriesService {


    /**
     * 创建数据集
     *
     * @param key    键
     * @param labels 标签
     * @return
     */
    boolean tsCreate(String key, Map<String, String> labels);

    /**
     * 添加数据
     *
     * @param key    键
     * @param tsData 数据
     * @return
     */
    boolean tsAdd(String key, TsData tsData);

    /**
     * 获取数据
     *
     * @param key 键
     * @return
     */
    TsData getLastData(String key);

    /**
     * 获取数据，聚合计算
     *
     * @param key        键
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param cal        聚合计算方法
     * @param timeBucket 窗口大小
     * @return
     */
    List<TsData> getRangeData(String key, String startTime, String endTime, Aggregation cal, long timeBucket);

    /**
     * 过滤数据，聚合计算
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param cal        聚合计算方法
     * @param timeBucket 窗口大小
     * @param filters    过滤条件 按标签过滤
     * @return
     */
    List<TsMData> getDataByFilters(String startTime, String endTime, Aggregation cal, long timeBucket, String... filters);
}
