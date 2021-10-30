package com.available.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.available"})
public class AvailableHotelsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvailableHotelsServiceApplication.class, args);
    }

}
