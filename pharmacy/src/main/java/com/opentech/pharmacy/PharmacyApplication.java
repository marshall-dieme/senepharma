package com.opentech.pharmacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.opentech.pharmacy.application")
public class PharmacyApplication {
    public static void main(String[] args) {
        SpringApplication.run(PharmacyApplication.class, args);
    }
}
