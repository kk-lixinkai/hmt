package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.NodeData;

public interface NodeDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NodeData record);

    int insertSelective(NodeData record);

    NodeData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NodeData record);

    int updateByPrimaryKey(NodeData record);
}