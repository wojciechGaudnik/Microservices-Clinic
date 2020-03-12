package com.clinics.common.bean;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		objectMapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
		objectMapper.setDateFormat(dateFormat);
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
				.setSkipNullEnabled(true);
//				.setFieldMatchingEnabled(true)
//				.setCollectionsMergeEnabled(true)
//				.setDeepCopyEnabled(false);
		return modelMapper;
	}
}
