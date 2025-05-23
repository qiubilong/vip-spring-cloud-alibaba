package com.tuling.mall.ribbondemo.interceptor;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Fox
 */
@Configuration
public class MyLoadBalancerAutoConfiguration {
    
    @MyLoadBalanced
    @Autowired(required = false) // 限定注入到list的RestTemplate
    private List<RestTemplate> restTemplates = Collections.emptyList();
    
    @Bean
    public MyLoadBalancerInterceptor myLoadBalancerInterceptor(LoadBalancerClient loadBalancerClient) {
        return new MyLoadBalancerInterceptor(loadBalancerClient);
    }
    
    
    @Bean
    public SmartInitializingSingleton myLoadBalancedRestTemplateInitializer(
            MyLoadBalancerInterceptor myLoadBalancerInterceptor) {
        //  spring的扩展点
        return new SmartInitializingSingleton() {
            @Override
            public void afterSingletonsInstantiated() {
                for (RestTemplate restTemplate : MyLoadBalancerAutoConfiguration.this.restTemplates) {
                    List<ClientHttpRequestInterceptor> list = new ArrayList<>(restTemplate.getInterceptors());
                    // 填充拦截器
                    list.add(myLoadBalancerInterceptor);
                    restTemplate.setInterceptors(list);
                }
            }
        };
    }
    
    
}
