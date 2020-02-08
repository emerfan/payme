package com.payme.api.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.payme.api.model.JwtAuthenticationToken;
import com.payme.api.model.JwtUser;
import com.payme.api.model.JwtUserDetails;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtValidator validator;

	@Override
	public boolean supports(Class<?> arg0) {
		return JwtAuthenticationToken.class.isAssignableFrom(arg0);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken userPassToken)
			throws AuthenticationException {
		JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) userPassToken;
		String token = jwtToken.getToken();
		JwtUser user = validator.validate(token);

		if (user == null) {
			throw new BadCredentialsException("Bearer is incorrect");
		}

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());

		return new JwtUserDetails(user.getUserName(), user.getId(), token, grantedAuthorities);
	}

}
