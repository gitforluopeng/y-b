package com.lp.service.impl.system;

import java.util.List;
import java.lang.Integer;
import java.util.Map;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lp.entity.system.Resource;
import com.lp.service.system.ResourceService;
import com.lp.dao.system.ResourceMapper;

/**
 * @author PengLuo
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceMapper resourceMapper;

    @Autowired
    public ResourceServiceImpl(ResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @Override
    public Resource selectByPrimaryKey(Long id){
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Resource> select(Resource resource) {
        return resourceMapper.select(resource);
    }

    @Override
    public List<Resource> selectPage(Map<String, Object> params) {
        return resourceMapper.selectPage(params);
    }

    @Override
    public Integer count(Resource resource) {
        return resourceMapper.count(resource);
    }

    @Override
    @Transactional
    public Integer insert(Resource resource) {
        resource.setId(IdUtil.getSnowflake(1, 1).nextId());
        return resourceMapper.insert(resource);
    }

    @Override
    @Transactional
    public Integer insertBatch(List<Resource> list) {
        return resourceMapper.insertBatch(list);
    }

    @Override
    @Transactional
    public Integer update(Resource resource) {
        return resourceMapper.update(resource);
    }

    @Override
    @Transactional
    public Integer delete(Long id) {
        return resourceMapper.delete(id);
    }

    @Override
    @Transactional
    public Integer deleteBatch(List<Long> ids) {
        return resourceMapper.deleteBatch(ids);
    }

}