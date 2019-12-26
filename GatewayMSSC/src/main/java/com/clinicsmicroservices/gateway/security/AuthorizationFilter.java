package com.clinicsmicroservices.gateway.security;

import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	Environment environment;

	public AuthorizationFilter(AuthenticationManager auth, Environment environment) {
		super(auth);
		this.environment = environment;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));

		if (authorizationHeader == null || !authorizationHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));

		if (authorizationHeader == null) {
			return null;
		}

		String token = authorizationHeader.replace(environment.getProperty("authorization.token.header.prefix") + " ", "");
		String userId = Jwts.parser()
				.setSigningKey("23f2f12Ff24f2144f2")
				.parseClaimsJws(token)
				.getBody()
				.getSubject();

		if (userId == null) {
			return null;
		}

		return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
	}
}
