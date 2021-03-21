package com.mybestcoding.hmt.service.impl;

import com.mybestcoding.hmt.mapper.TerminalDeviceMapper;
import com.mybestcoding.hmt.mapper.WareHouseMapper;
import com.mybestcoding.hmt.model.WareHouse;
import com.mybestcoding.hmt.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lixinkai
 * @description: 仓库服务实现类
 * @date: 2021/3/20 17:07
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class WareHouseServiceImpl implements WarehouseService {

    private final WareHouseMapper wareHouseMapper;
    private final TerminalDeviceMapper terminalDeviceMapper;

    public WareHouseServiceImpl(WareHouseMapper wareHouseMapper, TerminalDeviceMapper terminalDeviceMapper) {
        this.wareHouseMapper = wareHouseMapper;
        this.terminalDeviceMapper = terminalDeviceMapper;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public WareHouse getOne(Integer id) {
        return wareHouseMapper.selectByPrimaryKey(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public List<WareHouse> getAll() {
        return wareHouseMapper.selectAll();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int addNewWareHouse(WareHouse wareHouse) {
        return wareHouseMapper.insertSelective(wareHouse);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int modifyWareHouse(WareHouse wareHouse) {
        return wareHouseMapper.updateByPrimaryKeySelective(wareHouse);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public int removeWareHouse(Integer wid, Integer did) {
        // 解除终端与仓库的绑定
        int unBindResult = terminalDeviceMapper.deleteTdWithWareHouse(wid, did);
        if (unBindResult <= 0) {
            throw new RuntimeException("解除终端与仓库的绑定失败");
        }
        // 移除仓库信息
        return wareHouseMapper.deleteByPrimaryKey(wid);
    }
}
