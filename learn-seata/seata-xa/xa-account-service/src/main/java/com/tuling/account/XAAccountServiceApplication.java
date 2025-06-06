package com.tuling.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tuling")
public class XAAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XAAccountServiceApplication.class, args);
    }

}
