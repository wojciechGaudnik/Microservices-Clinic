package com.clinics.patient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	final ApiInfoBuilder apiInfoBuilder;

	public Swagger2Config(ApiInfoBuilder apiInfoBuilder) {
		this.apiInfoBuilder = apiInfoBuilder;
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.clinics.patient.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiEndPointsInfo());
	}

	private ApiInfo apiEndPointsInfo(){
		return this.apiInfoBuilder
				.description("Patient Rest API")
				.version("0.0.1-SNAPSHOT")
				.build();
	}
}
