package com.tuling.mall.ribbondemo.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import com.tuling.mall.ribbondemo.rule.NacosRandomWithWeightRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fox
 */
@Configuration
public class RibbonConfig {

    /**
     * 全局配置
     * 指定负载均衡策略
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        // 指定使用Nacos提供的负载均衡策略（优先调用同一集群的实例，基于随机权重）
        return new NacosRule();
    }
}
