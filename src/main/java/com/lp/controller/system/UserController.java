package com.lp.controller.system;

import java.util.List;
import java.lang.Integer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lp.base.*;
import com.github.pagehelper.Page;
import com.lp.entity.system.User;
import com.lp.service.system.UserService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author PengLuo
 */
@RestController
@RequestMapping("/system/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public BaseResponse<User> selectByPrimaryKey(@PathVariable Long id) {
        BaseResponse<User> response = new BaseResponse<>();
        response.setData(userService.selectByPrimaryKey(id));
        return response;
    }

    @GetMapping
    public BaseResponse<List<User>> select(User user) {
        BaseResponse<List<User>> response = new BaseResponse<>();
        response.setData(userService.select(user));
        return response;
    }
    
    @GetMapping("/page")
    public BaseResponse<PageInfo<User>> selectPage(User user, PageParam pageParam) {
        BaseResponse<PageInfo<User>> response = new BaseResponse<>();
        Map<String, Object> params = user.toMap();
        //分页参数
        params.putAll(pageParam.toMap());
        Page<User> page = (Page<User>) userService.selectPage(params);
        response.setData(new PageInfo<>(page.getTotal(), page.getResult(), page.getPages()));
        return response;
    }
    
    @PostMapping
    public BaseResponse<Integer> insert(@RequestBody User user) {
        BaseResponse<Integer> response = new BaseResponse<>();
        Integer result = userService.insert(user);
        if (result < 0) {
            response.setCode("0");
            response.setMsg("系统已存在此用户名。");
        }
        response.setData(result);
        return response;
    }

    @PutMapping("/{id}")
    public BaseResponse<Integer> update(@PathVariable Long id, @RequestBody User user) {
        BaseResponse<Integer> response = new BaseResponse<>();
        user.setId(id);
        response.setData(userService.update(user));
        return response;
    }

    @DeleteMapping("/{ids}")
    public BaseResponse<Integer> deleteBatch(@PathVariable Long[] ids) {
        BaseResponse<Integer> response = new BaseResponse<>();
        response.setData(userService.deleteBatch(Stream.of(ids).distinct().collect(Collectors.toList())));
        return response;
    }

}