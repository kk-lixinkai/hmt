package com.mybestcoding.hmt.service;

import com.mybestcoding.hmt.model.WareHouse;

import java.util.List;

/**
 * 仓库服务接口
 */
public interface WarehouseService {

    /**
     * 获取 指定的仓库数据
     *
     * @param id
     * @return
     */
    WareHouse getOne(Integer id);

    /**
     * 获取所有仓库的信息
     *
     * @return
     */
    List<WareHouse> getAll();

    /**
     * 添加新的仓库信息
     *
     * @param wareHouse
     * @return
     */
    int addNewWareHouse(WareHouse wareHouse);

    /**
     * 修改仓库信息
     *
     * @param wareHouse
     * @return
     */
    int modifyWareHouse(WareHouse wareHouse);


    /**
     * 移除仓库信息
     *
     * @param wid
     * @param did
     * @return
     */
    int removeWareHouse(Integer wid, Integer did);
}
