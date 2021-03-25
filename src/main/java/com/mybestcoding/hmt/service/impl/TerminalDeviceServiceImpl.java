package com.mybestcoding.hmt.service.impl;

import com.mybestcoding.hmt.mapper.NodeMapper;
import com.mybestcoding.hmt.mapper.TerminalDeviceMapper;
import com.mybestcoding.hmt.model.TerminalDevice;
import com.mybestcoding.hmt.service.TerminalDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lixinkai
 * @description: 终端服务实现类
 * @date: 2021/3/20 17:14
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class TerminalDeviceServiceImpl implements TerminalDeviceService {

    @Autowired
    private TerminalDeviceMapper terminalDeviceMapper;

    @Autowired
    private NodeMapper nodeMapper;


    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public TerminalDevice getOne(Integer id) {
        return terminalDeviceMapper.selectByPrimaryKey(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public List<TerminalDevice> getAllByWareHouseId(Integer id) {
        return terminalDeviceMapper.selectByWarehouseId(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int modifyTerminalDevices(TerminalDevice terminalDevice) {
        return terminalDeviceMapper.updateByPrimaryKeySelective(terminalDevice);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int addTerminalDevice(TerminalDevice terminalDevice, Integer wid) {
        int result = terminalDeviceMapper.insertSelective(terminalDevice);
        if (terminalDevice.getId() <= 0) {
            throw new RuntimeException("添加终端失败");
        }
        int bindResult = terminalDeviceMapper.insertTdToWareHouse(terminalDevice.getId(), wid);
        return bindResult;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public int removeTerminalDevice(Integer wid, Integer did) {
        // 解除绑定
        int unBindResult = terminalDeviceMapper.deleteTdWithWareHouse(wid);
        if (unBindResult <= 0) {
            throw new RuntimeException("删除失败，终端无法从所属仓库移除");
        }
        // 清空该终端下的节点
        int result = nodeMapper.deleteByDid(did);
        if (result <= 0) {
            throw new RuntimeException("删除失败，清空终端下所有的节点信息失败");
        }
        // 删除终端信息
        return terminalDeviceMapper.deleteByPrimaryKey(did);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int bind(Integer wid, Integer did) {
        return terminalDeviceMapper.insertTdToWareHouse(did, wid);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int unBind(Integer wid, Integer did) {
        return terminalDeviceMapper.deleteTdWithWareHouse(wid);
    }
}
