package com.emer.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.emer.api.model.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	public JwtAuthenticationTokenFilter() {
		super("/rest/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String header = request.getHeader("Authorization");

		if(header == null || !header.startsWith("Token ")) {
			throw new RuntimeException("Token is missing");
		}
		
		String token = header.substring(6);
		JwtAuthenticationToken jwtToken = new JwtAuthenticationToken(token);
		return getAuthenticationManager().authenticate(jwtToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authResult) throws IOException, ServletException {
			super.successfulAuthentication(request, response, chain, authResult);
			chain.doFilter(request, response);
	}

}
