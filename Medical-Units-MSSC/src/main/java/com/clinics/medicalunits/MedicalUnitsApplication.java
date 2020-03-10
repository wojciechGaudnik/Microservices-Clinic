package com.clinics.medicalunits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MedicalUnitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalUnitsApplication.class, args);
	}

}
