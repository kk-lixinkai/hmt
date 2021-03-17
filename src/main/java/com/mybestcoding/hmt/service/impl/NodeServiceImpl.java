package com.mybestcoding.hmt.service.impl;

import java.util.Date;
import java.util.List;

import com.mybestcoding.hmt.constant.NodeDTO;
import com.mybestcoding.hmt.mapper.NodeMapper;
import com.mybestcoding.hmt.model.Node;
import com.mybestcoding.hmt.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author: lixinkai
 * @description: 节点操作
 * @date: 2021/3/16 11:09
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeMapper nodeMapper;

    @Cacheable(value = "node", key = "'node'+${id}")
    @Override
    public Node findOne(Integer id) {
        Node node = nodeMapper.selectByPrimaryKey(id);
        if (node == null) {
            throw new RuntimeException("该节点不存在");
        }
        return node;
    }

    @Override
    public List<Node> findAll() {
        List<Node> nodes = nodeMapper.selectAll();
        if (nodes.size() <= 0) {
            throw new RuntimeException("查询失败，数据不存在");
        }
        return nodes;
    }


    @CachePut(value = "node", key = "'node'+${node.id}")
    @Override
    public Node add(Node node) {
//        Node node = createNewNode(nodeDTO);
        nodeMapper.insertSelective(node);
        return node;
    }


    @CacheEvict(value = "node", key = "'node' + ${id}")
    @Override
    public int remove(Integer id) {
        return nodeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 创建一个新节点
     *
     * @param nodeDto
     * @return
     */
    private Node createNewNode(NodeDTO nodeDto) {
        Node newNode = new Node();
        newNode.setType(nodeDto.getType());
        newNode.setStatus(nodeDto.getStatus());
        newNode.setWid(nodeDto.getWid());
        newNode.setTid(nodeDto.getTid());
        newNode.setCreatedTime(new Date());
        newNode.setUpdatedTime(null);
        newNode.setDeleteTime(null);
        return newNode;
    }
}
