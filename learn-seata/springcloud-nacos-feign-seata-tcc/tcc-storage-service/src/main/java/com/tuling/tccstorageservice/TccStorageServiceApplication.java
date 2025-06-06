package com.tuling.tccstorageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tuling")
public class TccStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccStorageServiceApplication.class, args);
    }

}
