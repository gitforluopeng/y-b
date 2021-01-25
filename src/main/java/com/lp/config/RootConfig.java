package com.lp.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//Import与Configuration是互斥的（在被扫描的包下）
//@Import({DataSourceConfig.class})
@EnableTransactionManagement
//不扫描Controller
@ComponentScan(basePackages = {"com.lp"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class), @ComponentScan.Filter(type =FilterType.ANNOTATION,classes = Controller.class)})
public class RootConfig {

}
