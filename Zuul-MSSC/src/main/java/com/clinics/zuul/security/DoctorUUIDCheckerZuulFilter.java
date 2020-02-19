//package com.clinics.zuul.security;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.Charset;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.StreamUtils;
//
//import com.netflix.zuul.context.RequestContext;
//
//import static com.netflix.zuul.context.RequestContext.getCurrentContext;
//import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;
//
//
//@Slf4j
//public class DoctorUUIDCheckerZuulFilter extends ZuulFilter {
//	@Override
//	public String filterType() {
//		return "pre";  //todo POST !!!
//	}
//
//	@Override
//	public int filterOrder() {
//		return 6;
//	}
//
//	@Override
//	public boolean shouldFilter() {
//		RequestContext context = getCurrentContext();
//		log.warn("RequestContext context = getCurrentContext(); <-------------<-------------");
//		return true;
//	}
//
//	@Override
//	public Object run() throws ZuulException {
//
//		return null;
//	}
//}
