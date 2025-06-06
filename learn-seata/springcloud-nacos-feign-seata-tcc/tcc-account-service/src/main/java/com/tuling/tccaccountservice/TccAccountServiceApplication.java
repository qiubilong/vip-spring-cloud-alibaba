package com.tuling.tccaccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tuling")
public class TccAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccAccountServiceApplication.class, args);
    }

}
