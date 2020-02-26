package com.clinics.doctors.configuration;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter methodParameter, Class aClass) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(
			Object o,
			MethodParameter methodParameter,
			MediaType mediaType,
			Class<? extends HttpMessageConverter<?>> aClass,
			ServerHttpRequest serverHttpRequest,
			ServerHttpResponse serverHttpResponse) {
		serverHttpResponse.getHeaders().set("Content-Type", "application/json");
		return o;
	}
}
