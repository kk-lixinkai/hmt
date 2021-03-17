package com.mybestcoding.hmt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybestcoding.hmt.constant.NodeStatus;
import com.mybestcoding.hmt.constant.TsData;
import com.mybestcoding.hmt.constant.TsNodeDataPackage;
import com.mybestcoding.hmt.service.NodeDataService;
import com.mybestcoding.hmt.util.RedisTimeSeriesUtil;
import com.mybestcoding.hmt.util.RedisUtil;
import com.redislabs.redistimeseries.Aggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lixinkai
 * @description: 节点服务
 * @date: 2021/3/10 18:09
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class NodeDataServiceImpl implements NodeDataService {

    @Autowired
    private RedisTimeSeriesUtil rtsu;

    @Autowired
    private RedisUtil redisUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void receiveAndSave(String dataPackage) throws JsonProcessingException {
        // 解析数据包
        TsNodeDataPackage tsNodeDataPackage = parseJsonString(dataPackage);
        // 如果数据集不存在
        if (!redisUtil.hasKey(tsNodeDataPackage.getKey())) {
            // 创建数据集
            createTS(tsNodeDataPackage);
        }
        // 保存数据
        rtsu.tsAdd(tsNodeDataPackage.getKey(), tsNodeDataPackage.getTimestamp(), tsNodeDataPackage.getValue());
        // 设置节点状态
        String nodeStatueKey = tsNodeDataPackage.getKey() + "s";
        setNodeStatus(nodeStatueKey, NodeStatus.OK.getStatus());
    }

    @Override
    public List<String> getTopics() {
        return null;
    }


    @Override
    public List<TsData> nodeDataAggCal(String key, long startTime, long endTime, Aggregation cal, long bucketTime) {
        return rtsu.tsRange(key, startTime, endTime, cal, bucketTime);
    }

    @Override
    public TsData newlyNodeData(String key) {
        return rtsu.tsGet(key);
    }

    @Override
    public String newlyNodeStatus(String key) {
        return null;
    }


    /**
     * 当数据集不存在时，创建一个新的数据集
     *
     * @param tsNodeDataPackage
     */
    private void createTS(TsNodeDataPackage tsNodeDataPackage) {
        Map<String, String> labels = new HashMap<>();
        labels.put("warehouse", tsNodeDataPackage.getWid());
        labels.put("device", tsNodeDataPackage.getDid());
        labels.put("node", tsNodeDataPackage.getNid());
        rtsu.tsCreate(tsNodeDataPackage.getKey(), labels);
    }

    /**
     * 解析数据包
     *
     * @param dataPackage 数据包Json 字符串
     * @return {@link TsNodeDataPackage} 对象
     */
    private TsNodeDataPackage parseJsonString(String dataPackage) throws JsonProcessingException {
        TsNodeDataPackage tsNodeDataPackage = objectMapper.readValue(dataPackage, TsNodeDataPackage.class);
        if (tsNodeDataPackage.getWid() == null || tsNodeDataPackage.getDid() == null || tsNodeDataPackage.getNid() == null ||
                tsNodeDataPackage.getType() == null || tsNodeDataPackage.getValue() == 0) {
            throw new RuntimeException("数据包错误!");
        }
        return tsNodeDataPackage;
    }


    /**
     * 设置节点状态
     * <p>
     * 如果某个键过期，则表明该键在规定的时间内没有上传数据，视为故障
     *
     * @param nodeId
     * @param nodeStatus
     */
    private void setNodeStatus(String nodeId, String nodeStatus) {
        if (redisUtil.hasKey(nodeId)) {
            redisUtil.expire(nodeId, 30);
            return;
        }
        redisUtil.set(nodeId, nodeStatus);
    }
}
