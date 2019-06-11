package com.emer.api.model;

import org.springframework.http.HttpStatus;

public class AuthorizationResponse {
	
	private String token;
	
	private String invalidUser;
	
	private HttpStatus status;

	public AuthorizationResponse() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getInvalidUser() {
		return invalidUser;
	}

	public void setInvalidUser(String invalidUser) {
		this.invalidUser = invalidUser;
	}
}
