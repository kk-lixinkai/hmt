package com.mybestcoding.hmt.service;

import com.mybestcoding.hmt.model.Node;

import java.util.List;

/**
 * 节点操作
 */
public interface NodeService {


    /**
     * 查找节点信息
     *
     * @param id
     * @return
     */
    Node findOne(Integer id);


    /**
     * 获取所有节点信息
     *
     * @return
     */
    List<Node> findAll();



    /**
     * 添加节点
     *
     * @param node
     * @return
     */
    Node add(Node node);

    /**
     * 删除节点
     *
     * @param id
     * @return
     */
    int remove(Integer id);
}
