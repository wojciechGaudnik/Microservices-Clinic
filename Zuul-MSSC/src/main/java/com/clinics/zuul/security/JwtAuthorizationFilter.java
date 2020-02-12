package com.clinics.zuul.security;

import com.clinics.common.security.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends OncePerRequestFilter implements JwtProperties {

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader(TOKEN_REQUEST_HEADER);
		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = header.replace(TOKEN_PREFIX, "");
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(TOKEN_SECRET)
					.parseClaimsJws(token)
					.getBody();
			String userName = claims.getSubject();
			if (userName != null) {
				List<String> authorities = (List<String>) claims.get("authorities");
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
						userName,
						null,
						authorities
								.stream()
								.map(SimpleGrantedAuthority::new)
								.collect(Collectors.toList()));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e){
			SecurityContextHolder.clearContext();
		}
		filterChain.doFilter(request, response);
	}
}
