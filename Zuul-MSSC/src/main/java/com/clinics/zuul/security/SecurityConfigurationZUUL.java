package com.clinics.zuul.security;

import com.clinics.common.security.JwtProperties;
import com.clinics.common.security.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfigurationZUUL extends WebSecurityConfigurerAdapter implements Role, JwtProperties {

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
				.cors().and()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling().authenticationEntryPoint((request, response, e) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and()
				.addFilterAfter(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, TOKEN_LOGIN_URI).permitAll()
				.antMatchers("/doctor/**").hasRole(Role.DOCTOR)
				.anyRequest().authenticated();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList(
				"Access-Control-Allow-Headers",
				"Access-Control-Allow-Origin",
				"Access-Control-Request-Method",
				"Access-Control-Request-Headers",
				"Origin","Cache-Control",
				"Content-Type",
				"Authorization",
				"Pragma"
		));
		configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PUT", "OPTIONS", "PATCH"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
