//package com.clinics.zuul.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//import javax.servlet.annotation.WebFilter;
//
//@Slf4j
////@ControllerAdvice
//@WebFilter
//@Component
//public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {
//	@Override
//	public boolean supports(MethodParameter methodParameter, Class aClass) {
//		return true;
//	}
//
//	@Override
//	public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//		log.warn("------------------CustomResponseBodyAdvice-----------------------------------------------------------");
//		serverHttpResponse.getHeaders().set("Content-Type", "application/json");
//		return o;
//	}
//}
