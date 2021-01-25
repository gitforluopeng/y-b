package com.lp.service.impl.system;

import java.util.List;
import java.lang.Integer;
import java.util.Map;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lp.entity.system.Role;
import com.lp.service.system.RoleService;
import com.lp.dao.system.RoleMapper;

/**
 * @author PengLuo
 */
@Service
public class RoleServiceImpl implements RoleService {

    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Role selectByPrimaryKey(Long id){
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> select(Role role) {
        return roleMapper.select(role);
    }

    @Override
    public List<Role> selectPage(Map<String, Object> params) {
        return roleMapper.selectPage(params);
    }

    @Override
    public Integer count(Role role) {
        return roleMapper.count(role);
    }

    @Override
    @Transactional
    public Integer insert(Role role) {
        role.setId(IdUtil.getSnowflake(1, 1).nextId());
        return roleMapper.insert(role);
    }

    @Override
    @Transactional
    public Integer insertBatch(List<Role> list) {
        return roleMapper.insertBatch(list);
    }

    @Override
    @Transactional
    public Integer update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    @Transactional
    public Integer delete(Long id) {
        return roleMapper.delete(id);
    }

    @Override
    @Transactional
    public Integer deleteBatch(List<Long> ids) {
        return roleMapper.deleteBatch(ids);
    }

}