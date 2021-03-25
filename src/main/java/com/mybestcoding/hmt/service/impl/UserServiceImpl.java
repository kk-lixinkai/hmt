package com.mybestcoding.hmt.service.impl;

import com.mybestcoding.hmt.mapper.UserMapper;
import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lixinkai
 * @description: 用户服务实现类
 * @date: 2021/3/10 15:10
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Cacheable(value = "user", key = "'user_' + #userId")
    @Override
    public User getOne(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public List<User> getAll() {
        return userMapper.selectAllUsers();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CachePut(value = "user", key = "'user_' + #user.id")
    @Override
    public int modify(User user) {
//        // 更新用户信息修改时间
//        user.setUpdatedTime(new Date()).setDeleteTime(null);
        int updateResult = userMapper.updateByPrimaryKeySelective(user);
        if (updateResult <= 0) {
            throw new RuntimeException("用户信息修改失败");
        }
        // 返回更新后的用户信息
        return updateResult;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CachePut(value = "user", key = "'user_' + #user.id")
    @Override
    public int add(User user) {
        int result = userMapper.insertSelective(user);
        if (result <= 0) {
            throw new RuntimeException("添加用户信息失败");
        }
        return result;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CacheEvict(value = "user", key = "'user_'+#userId")
    @Override
    public int delete(int userId) {
        int delResult = userMapper.deleteByPrimaryKey(userId);
        if (delResult <= 0) {
            throw new RuntimeException("用户删除失败");
        }
        return delResult;
    }
}
