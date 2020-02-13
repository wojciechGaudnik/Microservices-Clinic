package com.clinics.auth.security;

import com.clinics.common.DTO.request.LoginUserDTO;
import com.clinics.common.security.JwtProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;


@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements JwtProperties {
	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(TOKEN_LOGIN_URI, "POST"));
	}

	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		try {
			LoginUserDTO loginUserDTO = new ObjectMapper().readValue(request.getInputStream(), LoginUserDTO.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					loginUserDTO.getUsername(),
					loginUserDTO.getPassword(),
					Collections.emptyList()
			);
			return authenticationManager.authenticate(authenticationToken);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication authResult) throws IOException {
		UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
		String token = Jwts.builder()
				.setSubject(authResult.getName())
				.claim("authorities", Collections.singletonList("ROLE_" + principal.getClinicUser().getRole()))
				.claim("UUID", principal.getClinicUser().getUuid())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
		response.addHeader(TOKEN_REQUEST_HEADER, TOKEN_PREFIX + token);
		response.getWriter().write("{\"" + TOKEN_REQUEST_HEADER + "\":\"" + TOKEN_PREFIX + token + "\"}");
	}
}
