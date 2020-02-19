//package com.clinics.zuul.security;
//
//import com.clinics.common.security.JwtProperties;
//import com.clinics.common.security.Role;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StreamUtils;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//
//import static com.netflix.zuul.context.RequestContext.getCurrentContext;
//import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;
//
//@Slf4j
////@Component
//public class DoctorUUIDCheckerGOOD extends ZuulFilter implements JwtProperties {
//
////	@Autowired
//	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//	public boolean checkDoctorUUID() throws IOException {
//		RequestContext requestContext = getCurrentContext();
////		InputStream inputStream = (InputStream) requestContext.get("requestEntity");
//		InputStream inputStream = requestContext.getRequest().getInputStream();
//		String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//		log.warn(body);
//		return true;
//	}
//
//	public String filterType() {
//		return "pre";
//	}
//
//	public int filterOrder() {
//		return 6;
//	}
//
//	public boolean shouldFilter() {
//		//todo Authentication authentication = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		log.warn(String.valueOf(authentication) + " <--------------------------------warn(String.valueOf(authentication))");
//
////		RequestContext context = getCurrentContext();
////		return context.getRequest().getParameter("service") == null;
//		return true;
//	}
//
//	public Object run() {
//		try {
//			RequestContext context = getCurrentContext();
//			InputStream in = (InputStream) context.get("requestEntity");
//			if (in == null) {
//				in = context.getRequest().getInputStream();
//			}
//			String body = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
//			log.warn(body);
//			String header = context.getRequest().getHeader(TOKEN_REQUEST_HEADER).replace(TOKEN_PREFIX, "");
//			System.out.println(header + " <------------------header");
////			log.warn(authentication.getName());
//			// body = "request body modified via set('requestEntity'): "+ body;
//			body = body.toUpperCase();
////			context.set("requestEntity", new ByteArrayInputStream(body.getBytes("UTF-8")));
//		}
//		catch (IOException e) {
//			rethrowRuntimeException(e);
//		}
//		return null;
//	}
//
//
//
////@SneakyThrows
////@Override
////public Object run() throws ZuulException {
////		RequestContext context = getCurrentContext();
////		InputStream in = (InputStream) context.get("requestEntity");
////		if (in == null) {
////		in = context.getRequest().getInputStream();
////		}
////		this.body = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
////		log.warn(body);
////		this.jwtToken = context.getRequest().getHeader(TOKEN_REQUEST_HEADER).replace(TOKEN_PREFIX, "");
////		log.warn(jwtToken + " <------------------header");
////		return null;
////		}
//}
