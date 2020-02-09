package com.clinics.zuul;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZuulApplication {

	@Value("${security.jwt.uri:/auth/**}")
	private String Uri;

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}


}
