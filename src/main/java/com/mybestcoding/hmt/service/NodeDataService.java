package com.mybestcoding.hmt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mybestcoding.hmt.constant.TsData;
import com.redislabs.redistimeseries.Aggregation;

import java.util.List;

/**
 * 节点服务
 */
public interface NodeDataService {


    /**
     * 接收并保节点数据
     *
     * @param dataPackage 节点数据包
     * @throws JsonProcessingException
     */
    void receiveAndSave(String dataPackage) throws JsonProcessingException;

    /**
     * 获取 所有订阅的主题
     *
     * @return
     */
    List<String> getTopics();


    /**
     * 节点聚合计算
     *
     * @param key        键
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param cal        计算方法
     * @param bucketTime 时间窗口
     * @return
     */
    List<TsData> nodeDataAggCal(String key, long startTime, long endTime, Aggregation cal, long bucketTime);

    /**
     * 获取最新节点数据
     *
     * @param key 键
     * @return 节点数据
     */
    TsData newlyNodeData(String key);


    /**
     * 获取最新节点状态
     *
     * @param key 键
     * @return 节点状态
     */
    String newlyNodeStatus(String key);


}
