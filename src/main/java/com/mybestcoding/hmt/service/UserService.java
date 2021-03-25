package com.mybestcoding.hmt.service;

import com.mybestcoding.hmt.model.User;

import java.util.List;

public interface UserService {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    User getOne(int userId);

    /**
     * 获取全部用户信息
     *
     * @return
     */
    List<User> getAll();

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    int modify(User user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    int delete(int userId);
}
