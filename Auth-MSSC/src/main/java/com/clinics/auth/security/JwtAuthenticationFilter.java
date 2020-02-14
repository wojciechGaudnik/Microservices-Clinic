package com.clinics.auth.security;

import com.clinics.auth.models.UserAuth;
import com.clinics.common.DTO.request.LoginUserDTO;
import com.clinics.common.DTO.response.UserResponseDTO;
import com.clinics.common.security.JwtProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.MDC;
import org.slf4j.MarkerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements JwtProperties, JwtMaker {

	private AuthenticationManager authenticationManager;
	private ObjectMapper objectMapper;
	private ModelMapper modelMapper;


	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(TOKEN_LOGIN_URI, "POST"));
		this.objectMapper = new ObjectMapper();
		this.modelMapper = new ModelMapper();
		this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		try {
			LoginUserDTO loginUserDTO = objectMapper.readValue(request.getInputStream(), LoginUserDTO.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					loginUserDTO.getEmail(),
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
		UserAuth userAuth = (UserAuth) authResult.getPrincipal();
		String token = makeJwtToken(userAuth);
		response.addHeader(TOKEN_REQUEST_HEADER, TOKEN_PREFIX + token);
		addUserDataIntoBody(response, userAuth, token);
	}

	private void addUserDataIntoBody(HttpServletResponse response, UserAuth userAuth, String token) throws IOException {
		UserResponseDTO userResponseDTO = modelMapper.map(userAuth, UserResponseDTO.class);
		userResponseDTO.setToken(TOKEN_PREFIX + token);
		objectMapper.writeValueAsString(userResponseDTO);
		response.getWriter().write(objectMapper.writeValueAsString(userResponseDTO));
	}
}
