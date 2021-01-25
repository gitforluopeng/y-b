package com.lp.controller.system;

import java.util.List;
import java.lang.Integer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lp.base.*;
import com.github.pagehelper.Page;
import com.lp.entity.system.Role;
import com.lp.service.system.RoleService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author PengLuo
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public BaseResponse<Role> selectByPrimaryKey(@PathVariable Long id) {
        BaseResponse<Role> response = new BaseResponse<>();
        response.setData(roleService.selectByPrimaryKey(id));
        return response;
    }

    @GetMapping
    public BaseResponse<List<Role>> select(Role role) {
        BaseResponse<List<Role>> response = new BaseResponse<>();
        response.setData(roleService.select(role));
        return response;
    }
    
    @GetMapping("/page")
    public BaseResponse<PageInfo<Role>> selectPage(Role role, PageParam pageParam) {
        BaseResponse<PageInfo<Role>> response = new BaseResponse<>();
        Map<String, Object> params = role.toMap();
        //分页参数
        params.putAll(pageParam.toMap());
        Page<Role> page = (Page<Role>) roleService.selectPage(params);
        response.setData(new PageInfo<>(page.getTotal(), page.getResult(), page.getPages()));
        return response;
    }
    
    @PostMapping
    public BaseResponse<Integer> insert(@RequestBody Role role) {
        BaseResponse<Integer> response = new BaseResponse<>();
        response.setData(roleService.insert(role));
        return response;
    }

    @PutMapping("/{id}")
    public BaseResponse<Integer> update(@PathVariable Long id, @RequestBody Role role) {
        BaseResponse<Integer> response = new BaseResponse<>();
        role.setId(id);
        response.setData(roleService.update(role));
        return response;
    }

    @DeleteMapping("/{ids}")
    public BaseResponse<Integer> deleteBatch(@PathVariable Long[] ids) {
        BaseResponse<Integer> response = new BaseResponse<>();
        response.setData(roleService.deleteBatch(Stream.of(ids).distinct().collect(Collectors.toList())));
        return response;
    }

}