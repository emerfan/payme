package com.emer.api.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = 2787528828318786659L;
	
	private String userName;
	
	private Long id;
	
	private String token;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public JwtUserDetails(String userName, Long id, String token, 
			List<GrantedAuthority> authorities) {
		this.userName = userName;
		this.id = id;
		this.token = token;
		this.authorities = authorities;	
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUserName() {
		return userName;
	}

	public Long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}
}
