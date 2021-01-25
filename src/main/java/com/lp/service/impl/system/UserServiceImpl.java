package com.lp.service.impl.system;

import java.util.List;
import java.lang.Integer;
import java.util.Map;
import java.util.Objects;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lp.entity.system.User;
import com.lp.service.system.UserService;
import com.lp.dao.system.UserMapper;
import org.springframework.util.StringUtils;

/**
 * @author PengLuo
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectByPrimaryKey(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> select(User user) {
        return userMapper.select(user);
    }

    @Override
    public List<User> selectPage(Map<String, Object> params) {
        return userMapper.selectPage(params);
    }

    @Override
    public Integer count(User user) {
        return userMapper.count(user);
    }

    @Override
    @Transactional
    public Integer insert(User user) {
        //验证用户名唯一性
        if (Objects.nonNull(loadUserByUsername(user.getUserName()))) return -1;
        user.setId(IdUtil.getSnowflake(1, 1).nextId());
        user.setUserPassword(new BCryptPasswordEncoder().encode(user.getUserPassword()));
        return userMapper.insert(user);
    }

    @Override
    @Transactional
    public Integer insertBatch(List<User> list) {
        return userMapper.insertBatch(list);
    }

    @Override
    @Transactional
    public Integer update(User user) {
        if (!StringUtils.isEmpty(user.getUserPassword())) user.setUserPassword(new BCryptPasswordEncoder().encode(user.getUserPassword()));
        return userMapper.update(user);
    }

    @Override
    @Transactional
    public Integer delete(Long id) {
        return userMapper.delete(id);
    }

    @Override
    @Transactional
    public Integer deleteBatch(List<Long> ids) {
        return userMapper.deleteBatch(ids);
    }

    @Override
    public User loadUserByUsername(String userName) {
        return userMapper.loadUserByUsername(userName);
    }

}