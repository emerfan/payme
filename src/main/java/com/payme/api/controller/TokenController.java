package com.payme.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payme.api.config.JwtGenerator;
import com.payme.api.model.AuthorizationResponse;
import com.payme.api.model.JwtUser;
import com.payme.api.service.JwtService;

/**
 * The TokenController class
 * Provides REST endpoints for Token related operations
 * 
 * @author emerfanning
 *
 */
@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	private JwtGenerator generator;
	
	@Autowired
	private JwtService jwtService;
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<AuthorizationResponse> generate(@RequestBody JwtUser user) {	
		AuthorizationResponse response = new AuthorizationResponse();
		
		if(jwtService.validateJwtUser(user)) {
			response.setToken(this.generator.generate(user));
			response.setStatus(HttpStatus.OK);
		} else {
			response.setInvalidUser("Invalid user details");
			response.setStatus(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<AuthorizationResponse>(response, response.getStatus());
	}
}
