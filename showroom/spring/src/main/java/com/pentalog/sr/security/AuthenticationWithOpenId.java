package com.pentalog.sr.security;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import com.pentalog.sr.model.Authorities;
import com.pentalog.sr.model.Users;
import com.pentalog.sr.service.AuthoritiesService;
import com.pentalog.sr.service.UserService;
import com.pentalog.sr.utils.AuthenticationUserDetails;

/**
 * Authentification with open id 
 * @authors acozma and bpopovici
 *
 */
public class AuthenticationWithOpenId implements
		AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
	/**
	 * The user service
	 */
	private UserService userService;
	/**
	 * The authority service
	 */
	private AuthoritiesService authoritiesService;
	
	/**
	 * The normalized open id attribute builder
	 */
	private NormalizedOpenIdAttributesBuilder normalizedOpenIdAttributesBuilder;

	/**
	 * Mandatory constructor
	 */
	protected AuthenticationWithOpenId() {
	}
	
	/**
	 * Constructor
	 * @param userService 
	 * @param authoritiesService
	 * @param normalizedOpenIdAttributesBuilder
	 */
	public AuthenticationWithOpenId(UserService userService,
			AuthoritiesService authoritiesService,
			NormalizedOpenIdAttributesBuilder normalizedOpenIdAttributesBuilder) {
		this.userService = userService;
		this.authoritiesService = authoritiesService;
		this.normalizedOpenIdAttributesBuilder = normalizedOpenIdAttributesBuilder;
	}

	/**
	 * Method that retrieves user data from the service provider and returns an authentication user details
	 */
	@Override
	public UserDetails loadUserDetails(
			OpenIDAuthenticationToken openIdAuthenticationToken)
			throws UsernameNotFoundException {

		String email = normalizedOpenIdAttributesBuilder
				.retrieveEmail(openIdAuthenticationToken);
		Users user = new Users();
		Authorities authorities = new Authorities();
		if (userService.getUserByUserName(email) == null) {
			user = userService.registerUserByEmail(email);
		} else {
			user = userService.getUserByUserName(email);
		}
		authorities = authoritiesService.getAuthorityByUsername(email);
		return new AuthenticationUserDetails(user, authorities);
	}
}