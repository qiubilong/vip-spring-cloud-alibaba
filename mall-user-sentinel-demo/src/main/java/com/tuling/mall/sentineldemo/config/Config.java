package com.tuling.mall.sentineldemo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenxuegui
 * @since 2025/5/21
 */
@Configuration(proxyBeanMethods = false)
public class Config {

    @Bean  /* ServletContextInitializer子类 。
    applicationContext扫描BeanDefinition后执行onRefresh() --> 创建Tomcat WebServer
    --> 找到所有ServletContextInitializer实例 --> onStart() --> 注册Filter到ServletContext */
    public FilterRegistrationBean<MyRequestFilter> filterRegistrationBean(){
        FilterRegistrationBean<MyRequestFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MyRequestFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;

    }
}
