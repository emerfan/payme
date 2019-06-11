package com.emer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emer.api.dao.JwtRepository;
import com.emer.api.model.JwtUser;

@Service
public class JwtService {
	
	@Autowired
	private JwtRepository jwtRepo;	
	
	public boolean validateJwtUser(JwtUser user) {
		Optional<JwtUser> validateUser = jwtRepo.findById(user.getId());
		if(validateUser.isPresent()) {
			JwtUser isValidUser = validateUser.get();
			if(isValidUser.getUserName().equals(user.getUserName()) &&
					isValidUser.getRole().equals(user.getRole())) {
				return true;
			} 
		}
		return false;
	}
}
