//package com.clinics.zuul.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpInputMessage;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.lang.reflect.Type;
//
//@Slf4j
//@Component
////@ControllerAdvice
//public class CustomRequestBodyAdviceAdapter extends HandlerInterceptorAdapter {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		ServletRequest servletRequest = new ContentCachingRequestWrapper(request);
//		log.warn("--------------------------------------------------------------------------------------------------------------------------------------------");
//		log.warn(String.valueOf(servletRequest.getParameterMap()));
//
//		return super.preHandle(request, response, handler);
//	}
//}
