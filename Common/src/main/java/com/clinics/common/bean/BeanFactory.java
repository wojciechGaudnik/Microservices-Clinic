package com.clinics.common.bean;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class BeanFactory implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

//	public static <T> T getBean(Class<T> beanClass) {
//		return context.getBean(beanClass);
//	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ObjectMapper getObjectMapper(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper
				.enable(JsonParser.Feature.ALLOW_COMMENTS)
				.setDateFormat(dateFormat)
				.registerModules(
						new ParameterNamesModule(),
						new Jdk8Module(),
						new JavaTimeModule());
		return objectMapper;
	}

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder(){
		return new Jackson2ObjectMapperBuilder() {
			@Override
			public void configure(ObjectMapper objectMapper) {
				super.configure(objectMapper);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
				objectMapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
				objectMapper.setDateFormat(dateFormat);
			}
		};
	}


	@Bean
	public ModelMapper getModelMapper(){
		var modelMapper = new ModelMapper();
		modelMapper
				.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(true);
//				.setFieldMatchingEnabled(true)
//				.setCollectionsMergeEnabled(true)
//				.setDeepCopyEnabled(false);
		return modelMapper;
	}

	@Bean
	public ApiInfoBuilder apiInfoBuilder(){
		return new ApiInfoBuilder()
				.title("E-Clinics REST API")
				.contact(new Contact("Clinic team", "www.janusze.microserices", "janusz.januszewski@janusz.land.pl"))
				.license("Free")
				.licenseUrl("free.com");
	}
	//todo
	// 	@Bean
	//	@LoadBalanced
	//	public RestTemplate getRestTemplate(){
	//		return new RestTemplate(getClientHttpRequestFactory());
	//	}
	//
	//	private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory(){
	//		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory
	//				= new HttpComponentsClientHttpRequestFactory();
	//		httpComponentsClientHttpRequestFactory.setConnectTimeout(2000);
	//		httpComponentsClientHttpRequestFactory.setReadTimeout(2000);
	//		return httpComponentsClientHttpRequestFactory;
	//	}
}
