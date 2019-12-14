package com.clinicsmicroservices.doctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//todo check if override data-rest end point working <--- probably Yes but we need more tests
//todo "_embedded" if we decide to use data-rest is it necessary to remove this "fither"
//todo set the same pass and user for postgres and user tests

@SpringBootApplication
@EnableConfigurationProperties
public class DoctorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorApplication.class, args);
	}

}
