package com.lp.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class MyLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public MyLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    //commence方法是返回关键

}
