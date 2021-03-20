package com.mybestcoding.hmt.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.mybestcoding.hmt.mapper.RoleMapper;
import com.mybestcoding.hmt.model.Role;
import com.mybestcoding.hmt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: lixinkai
 * @description: 角色服务实现类
 * @date: 2021/3/10 12:26
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public void add(String roleName) {
        if (StringUtil.isEmpty(roleName)) {
            throw new RuntimeException("角色名不能为空!");
        }
        Role role = new Role().setRole(roleName).setCreatedTime(new Date()).setUpdatedTime(null).setDeleteTime(null);
        roleMapper.insertSelective(role);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public void bindRole(int userId, int roleId) {
        roleMapper.bindRole(userId, roleId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public List<String> getAllByUserId(int userId) {
        List<String> roleNames = roleMapper.selectRoleByUserId(userId);
        return roleNames;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Cacheable(value = "role", key = "${roleNames}")
    @Override
    public List<String> getAll() {
        List<String> roleNames = roleMapper.selectAllRoles();
        return roleNames;
    }
}
