package com.emer.api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emer.api.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	@Value( "${jwt.secret}" )
	private String secret;
	
	public JwtUser validate(String token) {
		JwtUser user = null;
		try {
			Claims claims = (Claims) Jwts.parser()
					.setSigningKey(secret)
					.parse(token)
					.getBody();
			
			user = new JwtUser();
			
			user.setUserName(claims.getSubject());
			user.setId(Long.parseLong((String) claims.get("userId")));
			user.setRole((String) claims.get("role"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;	
	}

}
