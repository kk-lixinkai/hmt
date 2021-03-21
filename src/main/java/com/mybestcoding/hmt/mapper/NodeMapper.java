package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.Node;

import java.util.List;

public interface NodeMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByWid(Integer wid);

    int deleteByDid(Integer did);

    int insert(Node record);

    int insertSelective(Node record);

    Node selectByPrimaryKey(Integer id);

    List<Node> selectAll();

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);
}