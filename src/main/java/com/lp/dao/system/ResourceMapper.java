package com.lp.dao.system;

import java.util.List;
import java.lang.Integer;
import java.util.Map;
import com.lp.entity.system.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author PengLuo
 */
public interface ResourceMapper {

    /**
     *
     * 根据主键查询
     * @param id 主键
     * @return 实例对象
     */
    Resource selectByPrimaryKey(Long id);

    /**
     *
     * 动态查询
     * @param resource 实例对象
     * @return 对象列表
     */
    List<Resource> select(Resource resource);

    /**
     *
     * 分页查询
     * @param params 查询参数，包含分页信息
     * @return 对象列表
     */
    List<Resource> selectPage(Map<String, Object> params);

    /**
     *
     * 统计
     * @param resource 实例对象参数
     * @return 数据条数
     */
    Integer count(Resource resource);

    /**
     *
     * 新增
     * @param resource 实例对象
     * @return 影响行数
     */
    Integer insert(Resource resource);

    /**
     *
     * 批量插入
     * @param list 实例对象集合
     * @return 影响行数
     */
    Integer insertBatch(List<Resource> list);

    /**
     *
     * 修改
     * @param resource 实例对象
     * @return 影响行数
     */
    Integer update(Resource resource);

    /**
     *
     * 删除
     * @param id 主键
     * @return 影响行数
     */
    Integer delete(Long id);

    /**
     *
     * 批量删除
     * @param ids 主键
     * @return 影响行数
     */
    Integer deleteBatch(List<Long> ids);

}