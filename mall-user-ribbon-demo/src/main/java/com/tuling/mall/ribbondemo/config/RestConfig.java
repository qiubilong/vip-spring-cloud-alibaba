package com.tuling.mall.ribbondemo.config;

import com.tuling.mall.ribbondemo.interceptor.MyLoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Fox
 */
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    //@MyLoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public RestTemplate restTemplate(LoadBalancerInterceptor loadBalancerInterceptor) {
//        RestTemplate restTemplate = new RestTemplate();
//        //注入loadBalancerInterceptor拦截器
//        restTemplate.setInterceptors(Arrays.asList(loadBalancerInterceptor));
//        return restTemplate;
//    }
    

}
