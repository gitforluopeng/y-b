package com.lp.security;

import com.lp.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthForwardController {

    @PostMapping("/success")
    public BaseResponse<String> success() {
        BaseResponse<String> response = new BaseResponse<>();
        response.setData("登录成功");
        return response;
    }

    @PostMapping("/failure")
    public BaseResponse<String> failure() {
        BaseResponse<String> response = new BaseResponse<>();
        response.setData("登录失败");
        return response;
    }

    @PostMapping("/fail")
    public BaseResponse<String> fail() {
        BaseResponse<String> response = new BaseResponse<>();
        response.setData("登录失败.");
        return response;
    }

    @GetMapping("/session-time-out")
    public BaseResponse<String> sessionTimeOut() {
        BaseResponse<String> response = new BaseResponse<>();
        response.setCode("0");
        response.setData("登录过期");
        response.setStatus(HttpStatus.UNAUTHORIZED);
        return response;
    }

    @GetMapping("/un-auth")
    public BaseResponse<String> unAuth() {
        BaseResponse<String> response = new BaseResponse<>();
        response.setCode("0");
        response.setData("未登录");
        response.setStatus(HttpStatus.UNAUTHORIZED);
        return response;
    }
}
