package com.clinics.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PatientApp {
    public static void main(String[] args) {
        SpringApplication.run(PatientApp.class, args);
    }
}

