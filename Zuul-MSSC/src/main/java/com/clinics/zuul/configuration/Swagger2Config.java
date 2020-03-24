package com.clinics.zuul.configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Primary
@Component
@EnableSwagger2
public class Swagger2Config implements SwaggerResourcesProvider {

	@Override
	public List<SwaggerResource> get() {
		return new ArrayList<>(){{
			add(swaggerResource("Auth", "/auth/v2/api-docs", "2.0"));
			add(swaggerResource("Doctor", "/doctor-mssc/v2/api-docs", "2.0"));
			add(swaggerResource("Medical Unites", "/medical-units-mssc/v2/api-docs", "2.0"));
		}};
	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
		var swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}
}
