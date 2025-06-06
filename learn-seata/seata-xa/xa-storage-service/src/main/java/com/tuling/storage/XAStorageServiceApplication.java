package com.tuling.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tuling")
public class XAStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XAStorageServiceApplication.class, args);
    }

}
