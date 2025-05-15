package com.tuling.mall.feigndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //扫描和注册feign客户端bean定义
public class MallUserFeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUserFeignDemoApplication.class, args);
    }

}
