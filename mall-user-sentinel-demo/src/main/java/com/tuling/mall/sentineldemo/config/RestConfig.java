package com.tuling.mall.sentineldemo.config;


import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.tuling.mall.sentineldemo.sentinel.ExceptionUtil;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * @author Fox
 */
@Configuration
public class RestConfig {
    
    @Bean
    @LoadBalanced
    @SentinelRestTemplate(
            blockHandler = "handleBlockException",blockHandlerClass = ExceptionUtil.class,
            fallback = "handleFallback",fallbackClass = ExceptionUtil.class
    )
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
