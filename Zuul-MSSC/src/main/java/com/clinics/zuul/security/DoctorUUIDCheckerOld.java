//package com.clinics.zuul.security;
//
//import com.clinics.common.DTO.request.RegisterDoctorDTO;
//import com.clinics.common.bean.BeansFactory;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.netflix.zuul.context.RequestContext;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Arrays;
//
//
//@Slf4j
////@WebFilter
//@Component
//public class DoctorUUIDCheckerOld extends GenericFilterBean {
////public class DoctorUUIDChecker implements Filter {
//
//	final private ObjectMapper objectMapper;
//	@Autowired
//	public DoctorUUIDCheckerOld(ObjectMapper objectMapper) {
//		this.objectMapper = objectMapper;
//	}
//
//	@SneakyThrows
//	public boolean checkDoctorUUID(Authentication authentication, HttpServletRequest request) throws IOException {
//		log.warn("test from zul <--------- ++++++++++++++++++++++++++++++++++++");
//		RequestContext requestContext = RequestContext.getCurrentContext();
//		InputStream in = requestContext.getRequest().getInputStream();
////		requestContext.set
////		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
//
////		ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
////		log.warn(String.valueOf(requestWrapper.getContentAsByteArray().length));
////		log.warn(String.valueOf(requestWrapper.getReader().readLine()));
////		log.warn(String.valueOf(requestWrapper.getReader().readLine()));
////		log.warn(String.valueOf(requestWrapper.getReader().readLine()));
////		log.warn(String.valueOf(requestWrapper.getReader().readLine()));
////		log.warn(String.valueOf(requestWrapper.getReader().readLine()));
////		log.warn(String.valueOf(requestWrapper.getReader().readLine()));
////		RegisterDoctorDTO registerDoctorDTO = objectMapper.readValue(wrappedRequest.getInputStream(), RegisterDoctorDTO.class);
////		log.warn(registerDoctorDTO.getLastName() + " <--------------- lst name");
////		log.warn(Arrays.toString(wrappedRequest.getContentAsByteArray()));
////		log.warn(Arrays.toString(new String[]{wrappedRequest.getMethod()}));
////		log.warn(String.valueOf());
////		wrappedRequest.getReader().close();
////		log.warn(wrappedRequest.getRequestURI());
////		log.warn(String.valueOf(authentication.isAuthenticated()));
////		request.getInputStream().close();
//		return true;
//	}
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////		HttpServletRequest currentRequest = (HttpServletRequest) servletRequest;
////		ContentCachingRequestWrapper wrappedRequest = (ContentCachingRequestWrapper) currentRequest;
////		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(currentRequest);
//
//		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//		log.warn("++++++++++++++++++++++++++++++++++++");
////		log.warn(String.valueOf(wrappedRequest.getContentAsByteArray().length));
////		log.warn(String.valueOf(wrappedRequest.getMethod()));
////		log.warn(String.valueOf(wrappedRequest.getReader().readLine()));
////		log.warn(String.valueOf(wrappedRequest.getReader().readLine()));
////		log.warn(String.valueOf(wrappedRequest.getReader().readLine()));
////		log.warn(String.valueOf(wrappedRequest.getReader().readLine()));
////		log.warn(String.valueOf(wrappedRequest.getReader().readLine()));
////		log.warn(String.valueOf(wrappedRequest.getReader().readLine()));
////		RegisterDoctorDTO registerDoctorDTO = objectMapper.readValue(wrappedRequest.getContentAsByteArray(), RegisterDoctorDTO.class);
////		log.warn(registerDoctorDTO.getLastName() + " <--------------- lst name");
//		filterChain.doFilter(servletRequest, servletResponse);
////		filterChain.doFilter(wrappedRequest, servletResponse);
//
//	}
//
////	@Override
////	public void init(FilterConfig filterConfig) throws ServletException {
////
////	}
////
////	@Override
////	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////
////	}
////
////	@Override
////	public void destroy() {
////
////	}
//}