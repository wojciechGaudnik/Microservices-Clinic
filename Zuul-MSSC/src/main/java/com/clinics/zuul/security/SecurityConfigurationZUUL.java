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
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfigurationZUUL extends WebSecurityConfigurerAdapter implements Role, JwtProperties {

	//todo 401 Unauthorized – This means the user isn’t not authorized to access a resource. It usually returns when the user isn’t authenticated.
	//todo 403 Forbidden – This means the user is authenticated, but it’s not allowed to access a resource.
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

				.antMatchers(HttpMethod.GET, "/doctor-mssc/doctors/test/**").permitAll()
				.antMatchers(HttpMethod.GET, "/doctor-mssc/doctors/{uuid}").access("@userUUIDChecker.checkUserUUID(authentication, #uuid)")
				.antMatchers("/doctor-mssc/doctors/{uuid}/**").access("@userUUIDChecker.checkUserUUID(authentication, #uuid)")
				.antMatchers(HttpMethod.POST, "/doctor-mssc/doctors/").hasAnyRole(Role.DOCTOR)
				.antMatchers(HttpMethod.DELETE, "/doctor-mssc/doctors/{uuid}").access("@userUUIDChecker.checkUserUUID(authentication, #uuid)")
				.antMatchers(HttpMethod.PUT, "/doctor-mssc/doctors/{uuid}").access("@userUUIDChecker.checkUserUUID(authentication, #uuid)")
				.antMatchers(HttpMethod.PATCH, "/doctor-mssc/doctors/{uuid}").access("@userUUIDChecker.checkUserUUID(authentication, #uuid)")

				.antMatchers(HttpMethod.POST, "/patient-mssc/patients/").hasAnyRole(Role.PATIENT)
				.antMatchers(HttpMethod.POST, "/patient-mssc/patients/{uuid}/visit/**").access("@userUUIDChecker.checkUserUUID(authentication, #uuid)")
				.antMatchers(HttpMethod.DELETE, "/patient-mssc/patients/{uuid}/visit/**").access("@userUUIDChecker.checkUserUUID(authentication, #uuid)")
				.antMatchers(HttpMethod.PATCH, "/patient-mssc/patients/{uuid}/visit/**").access("@userUUIDChecker.checkUserUUID(authentication, #uuid)")

				.antMatchers(HttpMethod.POST, TOKEN_LOGIN_URI).permitAll()
				.antMatchers(HttpMethod.GET,"/auth/users/uuidAndRole/").authenticated()
				.antMatchers(HttpMethod.POST, "/auth/users/**").permitAll()
				.anyRequest().denyAll();
		http
				.headers()
				.addHeaderWriter(new StaticHeadersWriter("Content-Type", "application/json"));
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
