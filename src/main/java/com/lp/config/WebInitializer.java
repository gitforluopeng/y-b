package com.lp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 在 servlet3.0 环境下，web 容器启动时会自行去寻找类路径下所有实现了 WebApplicationInitializer 接口的 Initializer 实例，并调用他们的 onStartup 方法
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        //将DispatcherServlet映射到 /
        return new String[]{"/"};
    }

}
