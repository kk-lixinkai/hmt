package com.mybestcoding.hmt.service;


import com.mybestcoding.hmt.model.TerminalDevice;

import java.util.List;

/**
 * 终端服务
 */
public interface TerminalDeviceService {

    /**
     * 获取指定的终端信息
     *
     * @param id
     * @return
     */
    TerminalDevice getOne(Integer id);

    /**
     * 获取所有的终端信息
     *
     * @return
     */
    List<TerminalDevice> getAllByWareHouseId(Integer id);

    /**
     * 修改终端信息
     *
     * @param terminalDevice
     * @return
     */
    int modifyTerminalDevices(TerminalDevice terminalDevice);


    /**
     * 添加终端信息
     *
     * @param terminalDevice
     * @return
     */
    int addTerminalDevice(TerminalDevice terminalDevice, Integer wid);


    /**
     * 删除终端信息
     *
     * @param id
     * @return
     */
    int removeTerminalDevice(Integer id, Integer did);

    /**
     * 绑定
     *
     * @param wid
     * @param did
     * @return
     */
    int bind(Integer wid, Integer did);

    /**
     * 解绑
     *
     * @param wid
     * @param did
     * @return
     */
    int unBind(Integer wid, Integer did);

}
