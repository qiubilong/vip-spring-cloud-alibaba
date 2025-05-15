package com.tuling.mall.ribbondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientName;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@SpringBootApplication
public class MallUserRibbonDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUserRibbonDemoApplication.class, args);
    }
}
