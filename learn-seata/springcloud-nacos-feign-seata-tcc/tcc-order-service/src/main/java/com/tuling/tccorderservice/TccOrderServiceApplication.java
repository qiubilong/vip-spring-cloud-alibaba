package com.tuling.tccorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.tuling")
@EnableFeignClients
public class TccOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccOrderServiceApplication.class, args);
    }

}
