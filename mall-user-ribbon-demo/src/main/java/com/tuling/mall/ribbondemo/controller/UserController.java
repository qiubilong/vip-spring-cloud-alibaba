package com.tuling.mall.ribbondemo.controller;

import com.tuling.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/findOrderByUserId/{id}")
    public R  findOrderByUserId(@PathVariable("id") Integer id) {
        // restTemplate调用,url写死
        //String url = "http://localhost:8020/order/findOrderByUserId/"+id;
        //模拟ribbon实现
        //String url = getUri("mall-order")+"/order/findOrderByUserId/"+id;
        // ribbon实现，restTemplate需要添加@LoadBalanced注解
        String url = "http://mall-order/order/findOrderByUserId/"+id;

        R result = restTemplate.getForObject(url,R.class);

        return result;
    }


    @Autowired
    private DiscoveryClient discoveryClient;
    public String getUri(String serviceName) {
        // 获取服务的实例
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        if (serviceInstances == null || serviceInstances.isEmpty()) {
            return null;
        }
        int serviceSize = serviceInstances.size();
        //轮询
        int indexServer = incrementAndGetModulo(serviceSize);
        return serviceInstances.get(indexServer).getUri().toString();
    }

    private AtomicInteger nextIndex = new AtomicInteger(0);
    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextIndex.get();
            int next = (current + 1) % modulo;
            if (nextIndex.compareAndSet(current, next) && current < modulo){
                return current;
            }
        }
    }

}
