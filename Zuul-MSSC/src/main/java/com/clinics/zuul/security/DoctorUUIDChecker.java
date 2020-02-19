package com.clinics.zuul.security;

import com.clinics.common.security.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;


@Slf4j
@Component
public class DoctorUUIDChecker extends ZuulFilter implements JwtProperties {

	private String body;
	private String jwtToken;
//	private RequestContext requestContext;
//
//
//	public DoctorUUIDChecker() {
//		this.requestContext = new RequestContext();
//	}


//	public boolean checkDoctorUUID(Authentication authentication, HttpServletRequest request ) throws IOException {
//		log.warn("DoctorUUIDChecker ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		log.warn(body);
//		log.warn(jwtToken);

//		ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
//		log.warn(String.valueOf(contentCachingRequestWrapper));
//		log.warn(String.valueOf(contentCachingRequestWrapper.getRequest().getProtocol()));
//		log.warn(String.valueOf(contentCachingRequestWrapper.getRequest().getReader().readLine()));
//		RequestContext context = RequestContext.getCurrentContext();
//		InputStream in = (InputStream) context.get("requestEntity");
//		HttpServletRequest request = context.getRequest();
//		log.warn(String.valueOf(request));
//		InputStream in = context.getRequest().getInputStream();

//		String body = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
//		log.warn(body);

//		log.warn(String.valueOf(authentication.getAuthorities()));

//		String header = context.getRequest().getHeader(TOKEN_REQUEST_HEADER).replace(TOKEN_PREFIX, "");
//		System.out.println(header + " <------------------header");

//		ServletRequestWrapper test = new ServletRequestWrapper(request);
//		log.warn(Arrays.toString(test.getInputStream().readAllBytes()));

//		request = contentCachingRequestWrapper;
//		return true;
//	}



	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 6;
	}

	@Override
	public boolean shouldFilter() {
//		todo Authentication authentication = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
//		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
		return getCurrentContext().getRequest().getRequestURI().equals("/doctor-mssc/doctors/")
				&& getCurrentContext().getRequest().getMethod().equals("POST");
	}

	@SneakyThrows
//	@Override
//	public Object run() throws ZuulException {
//		log.warn("DoctorUUIDChecker ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//
////		log.warn(getCurrentContext().getRequest().getRemoteHost());
////		log.warn(requestContext.getRequest().getMethod());
//
//
//		RequestContext context = getCurrentContext();
//		InputStream in = (InputStream) context.get("requestEntity");
//		if (in == null) {
//			in = context.getRequest().getInputStream();
//		}
//		this.body = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
//		log.warn(body);
//		this.jwtToken = context.getRequest().getHeader(TOKEN_REQUEST_HEADER).replace(TOKEN_PREFIX, "");
//		log.warn(jwtToken + " <------------------header");
//		return null;
//	}

	public Object run() {
		log.warn("DoctorUUIDChecker ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.warn(String.valueOf(((HashMap<String, Object>)authentication.getCredentials()).get("UUID")));
		String uuidFromAuthentication = (String) ((HashMap<String, Object>) authentication.getCredentials()).get("UUID");
//		int uuid = authentication.getCredentials();

		try {
			RequestContext context = getCurrentContext();
			InputStream in = (InputStream) context.get("requestEntity");
			if (in == null) {
				in = context.getRequest().getInputStream();
			}
//			InputStream in = context.getRequest().getInputStream();
//			InputStream in = (InputStream) context.get("requestEntity");
			String body = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
			log.warn(body);
//			String header = context.getRequest().getHeader(TOKEN_REQUEST_HEADER).replace(TOKEN_PREFIX, "");
//			System.out.println(header + " <------------------header");
//			log.warn(authentication.getName());
//			 body = "request body modified via set('requestEntity'): "+ body;
//			body = "{\n" +
//					"\t\"firstName\": \"123\",\n" +
//					"\t\"lastName\": \"abc\",\n" +
//					"\t\"photoUrl\": \"asdfasdfasdfasdfasdfasdfasdfasdfasdf\",\n" +
//					"\t\"licence\": \"asdf23 rrasfd\",\n" +
//					"\t\"uuid\":\"505604a5-d8af-41d1-a6b7-0f796c4d7777\"" +
//					"}";
			//todo throw excepiton if "uuid" not exist
			log.warn(body);
			body = body.replaceAll("(\"uuid\"(\\s*):(\\s*)\")(.)*\"", "\"uuid\": \"" +
					uuidFromAuthentication + "\"");
			log.warn(body);
//			context.set(String.valueOf(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8))));
			context.set("requestEntity", new ByteArrayInputStream(body.getBytes("UTF-8")));
		}
		catch (IOException e) {
			rethrowRuntimeException(e);
		}
		return null;
	}
}
