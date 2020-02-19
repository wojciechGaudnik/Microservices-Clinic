package com.clinics.zuul.security;

import com.clinics.common.DTO.request.RegisterDoctorDTO;
import com.clinics.common.security.JwtProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter implements JwtProperties {

//	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
//		super(authenticationManager);
//	}


	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
//		ContentCachingRequestWrapper wrappedRequest = (ContentCachingRequestWrapper) request;
		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
//		RequestContext requestContext = RequestContext.getCurrentContext();
//		var wrappedRequest = request;
//		log.warn("JwtAuthorizationFilter ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		log.warn(String.valueOf(requestContext.getRequest()));
//		log.warn(String.valueOf(wrappedRequest.getMethod().equals("POST")));
//		log.warn(String.valueOf(wrappedRequest.getRequestURI().equals("/doctor-mssc/doctors/")));
//		ObjectMapper objectMapper = new ObjectMapper();
//		RegisterDoctorDTO registerDoctorDTO = objectMapper.readValue(wrappedRequest.getInputStream(), RegisterDoctorDTO.class);
//		log.warn(registerDoctorDTO.getLastName() + " <------ registerDoctorDTO.getLastName()");
		String header = wrappedRequest.getHeader(TOKEN_REQUEST_HEADER);
		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			filterChain.doFilter(wrappedRequest, response);
			return;
		}
		String token = header.replace(TOKEN_PREFIX, "");
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(TOKEN_SECRET)
					.parseClaimsJws(token)
					.getBody();
			String userName = claims.getSubject();
			String uuid = claims.get("UUID").toString();

			if (userName != null) {
				List<String> authorities = (List<String>) claims.get("authorities");
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
						userName,
						claims.get("UUID"),
						authorities
								.stream()
								.map(SimpleGrantedAuthority::new)
								.collect(Collectors.toList()));
//				log.warn(String.valueOf(wrappedRequest.getMethod().equals("POST")));
//				if (wrappedRequest.getMethod().equals("POST") && wrappedRequest.getRequestURI().equals("/doctor-mssc/doctors/")) {
//					log.warn("POST <--------------------------------- gff");
//					ObjectMapper objectMapper = new ObjectMapper();
//					log.warn(String.valueOf(wrappedRequest.getReader().readLine()));

//					RegisterDoctorDTO registerDoctorDTO = objectMapper.readValue(wrappedRequest.getInputStream(), RegisterDoctorDTO.class);
//					log.warn(registerDoctorDTO.getLastName() + " <------ registerDoctorDTO.getLastName()");
//					log.warn(wrappedRequest.getRequestURI().equals("/doctor-mssc/doctors/"));

//				}
//				log.warn("SecurityContextHolder.getContext().setAuthentication(auth); <-------------------");
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e){
			SecurityContextHolder.clearContext();
		}
		filterChain.doFilter(wrappedRequest, response);
	}
}
