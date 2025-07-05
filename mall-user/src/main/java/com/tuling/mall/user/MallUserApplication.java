package com.tuling.mall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class MallUserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MallUserApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        System.out.println(environment);
    }

}
