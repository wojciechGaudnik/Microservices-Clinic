package com.clinics.zuul.security;

import com.clinics.common.security.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

@Component
public class DoctorUUIDChecker extends ZuulFilter implements JwtProperties {


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

		var headers = getCurrentContext().getRequest().getHeaderNames().asIterator();
		return getCurrentContext().getRequest().getRequestURI().equals("/doctor-mssc/doctors/")  //todo asistant + Patient !!!
				&& getCurrentContext().getRequest().getMethod().equals("POST");
	}

	public Object run() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String uuidFromAuthentication = (String) ((HashMap<String, Object>) authentication.getCredentials()).get(TOKEN_CLAIM_UUID);  //todo sprawdź to, czy ma to sens jeśli autentykacja odbywa sie wcześniej
		try {
			RequestContext context = getCurrentContext();
			InputStream in = (InputStream) context.get("requestEntity");
			if (in == null) {
				in = context.getRequest().getInputStream();
			}
			String body = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
			//todo throw excepiton if "uuid" not exist
			body = body.replaceAll("(uuid\"(\\s*):(\\s*)\")(.)*\"", "uuid\": \"" +
					uuidFromAuthentication + "\"");
			context.set("requestEntity", new ByteArrayInputStream(body.getBytes("UTF-8")));
		}
		catch (IOException e) {
			rethrowRuntimeException(e);
		}

		return null;
	}
}
