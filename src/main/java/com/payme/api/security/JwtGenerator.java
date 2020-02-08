package com.payme.api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.payme.api.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	@Value( "${jwt.secret}" )
	private String secret;
	
	public String generate(JwtUser user) {
		Claims claims = Jwts.claims()
				.setSubject(user.getUserName());
		claims.put("userId", String.valueOf(user.getId()));
		claims.put("role", user.getRole());
		
		return Jwts.builder().setClaims(claims)
		.signWith(SignatureAlgorithm.HS512, secret)
		.compact();
	}
}
