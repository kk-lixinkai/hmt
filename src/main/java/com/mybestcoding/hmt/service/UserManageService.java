package com.mybestcoding.hmt.service;

import com.github.pagehelper.PageInfo;
import com.mybestcoding.hmt.model.User;

import java.util.List;

/**
 * 用户服务
 */
public interface UserManageService {

    /**
     * 增加用户
     *
     * @param user 用户信息
     * @return
     */
    int add(User user);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    int delete(Integer id);

    /**
     * 删除用户
     *
     * @param name 用户名
     * @return
     */
    int delete(String name);

    /**
     * 分页查询所有数据
     *
     * @param pageNo   页码
     * @param pageSize 每一页数据的记录数
     * @return
     */
    PageInfo<User> selectAll(int pageNo, int pageSize);

    /**
     * 查询用户
     *
     * @param id 用户id
     * @return
     */
    User selectOneById(Integer id);

    /**
     * 查询用户
     *
     * @param name 用户名
     * @return
     */
    User selectOneByUsername(String name);

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return
     */
    int modify(User user);

}
