package com.lp.controller.system;

import java.util.List;
import java.lang.Integer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lp.base.*;
import com.github.pagehelper.Page;
import com.lp.entity.system.Resource;
import com.lp.service.system.ResourceService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author PengLuo
 */
@RestController
@RequestMapping("/system/resource")
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/{id}")
    public BaseResponse<Resource> selectByPrimaryKey(@PathVariable Long id) {
        BaseResponse<Resource> response = new BaseResponse<>();
        response.setData(resourceService.selectByPrimaryKey(id));
        return response;
    }

    @GetMapping
    public BaseResponse<List<Resource>> select(Resource resource) {
        BaseResponse<List<Resource>> response = new BaseResponse<>();
        response.setData(resourceService.select(resource));
        return response;
    }
    
    @GetMapping("/page")
    public BaseResponse<PageInfo<Resource>> selectPage(Resource resource, PageParam pageParam) {
        BaseResponse<PageInfo<Resource>> response = new BaseResponse<>();
        Map<String, Object> params = resource.toMap();
        //分页参数
        params.putAll(pageParam.toMap());
        Page<Resource> page = (Page<Resource>) resourceService.selectPage(params);
        response.setData(new PageInfo<>(page.getTotal(), page.getResult(), page.getPages()));
        return response;
    }
    
    @PostMapping
    public BaseResponse<Integer> insert(@RequestBody Resource resource) {
        BaseResponse<Integer> response = new BaseResponse<>();
        response.setData(resourceService.insert(resource));
        return response;
    }

    @PutMapping("/{id}")
    public BaseResponse<Integer> update(@PathVariable Long id, @RequestBody Resource resource) {
        BaseResponse<Integer> response = new BaseResponse<>();
        resource.setId(id);
        response.setData(resourceService.update(resource));
        return response;
    }

    @DeleteMapping("/{ids}")
    public BaseResponse<Integer> deleteBatch(@PathVariable Long[] ids) {
        BaseResponse<Integer> response = new BaseResponse<>();
        response.setData(resourceService.deleteBatch(Stream.of(ids).distinct().collect(Collectors.toList())));
        return response;
    }

}