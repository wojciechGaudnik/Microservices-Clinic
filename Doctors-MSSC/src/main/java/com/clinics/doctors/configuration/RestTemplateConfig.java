package com.clinics.doctors.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
//		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
//
//
//		var restTemplate = new RestTemplateConfig();
//		restTemplate.
		return new RestTemplate();
	}
}
