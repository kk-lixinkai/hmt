package com.mybestcoding.hmt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybestcoding.hmt.mapper.UserMapper;
import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: lixinkai
 * @description: User服务类实现
 * @date: 2021/2/13 19:19
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserManageService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {

        return userMapper.insertSelective(setTimeAboutUser(user, true, true, true));
    }

    @Override
    public int delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(String name) {
        return userMapper.deleteByUserName(name);
    }

    @Override
    public PageInfo<User> selectAll(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<User> userInfos = new PageInfo<>(userMapper.selectAll());
        return userInfos;
    }

    @Override
    public User selectOneById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return this.nullOfUser(user);
    }

    @Override
    public User selectOneByUsername(String name) {
        User user = userMapper.selectByUsername(name);
        return this.nullOfUser(user);
    }

    @Override
    public int modify(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 判断用户信息是否有效
     *
     * @param user 待判断的信息
     * @return 有效的用户信息
     */
    private User nullOfUser(User user) {
        if (user.getId() != null) {
            return user;
        }
        throw new RuntimeException("不存在此用户的数据");
    }

    /**
     * 设置有关时间
     *
     * @param user
     * @return
     */
    private User setTimeAboutUser(User user, boolean modifyUpdateTime, boolean modifyLastLoginTime, boolean modifyCreateTime) {
        if (user != null) {
            if (modifyLastLoginTime) {
                user.setLastLoginTime(new Date());
            }
            if (modifyUpdateTime) {
                user.setUpdatedTime(new Date());
            }
            if (modifyCreateTime) {
                user.setCreatedTime(new Date());
            }
        }
        throw new RuntimeException("操作错误!");
    }
}
