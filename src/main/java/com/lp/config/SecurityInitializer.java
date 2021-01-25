package com.lp.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * security初始化类，用户注册过滤器
 * 这个类就有点意思，extends AbstractSecurityWebApplicationInitializer这个类，
 * 啥都没做，他就能注入org.springframework.web.filter.DelegatingFilterProxy，这得益于AbstractSecurityWebApplicationInitializer这个类的封装
 * 在 servlet3.0 环境下，web 容器启动时会自行去寻找类路径下所有实现了 WebApplicationInitializer 接口的 Initializer 实例，并调用他们的 onStartup 方法
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
