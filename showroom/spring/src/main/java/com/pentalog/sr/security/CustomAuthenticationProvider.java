package com.pentalog.sr.security;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.pentalog.sr.model.Authorities;
import com.pentalog.sr.model.Authorities.Authority;
import com.pentalog.sr.model.Users;
import com.pentalog.sr.service.AuthoritiesService;
import com.pentalog.sr.service.UserService;

/**
 * The LDAP and database authentication provider class
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	/**
	 * The user service
	 */
	@Autowired
	private UserService userService;

	/**
	 * The authority service
	 */
	@Autowired
	private AuthoritiesService authoritiesService;
	
	/**
	 * The ldap authentication provider
	 */
	@Autowired
	private ActiveDirectoryLdapAuthenticationProvider ldapAuthenticationProvider;

	/**
	 * Method that tries to successfully authenticate an user via database or ldap after verifying credentials
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		Authentication returnedAuthentication = null;
		Authority authority = Authority.USER;
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new BadCredentialsException("Empty credentials.");
		}
		authority = verifyCredentials(authentication, authority, username,
				password);
		Collection<GrantedAuthority> authoritiesList = new ArrayList<>();
		authoritiesList.add(new SimpleGrantedAuthority(authority.getLabel()));
		// don't store the password on the security context
		returnedAuthentication = new UsernamePasswordAuthenticationToken(
				username, null, authoritiesList);
		return returnedAuthentication;
	}

	/**
	 * Method that verifies user credentials
	 * @param authentication -	the authentication 
	 * @param authority -	the user authority
	 * @param username  -	the username
	 * @param password  -	the password
	 * @return
	 */
	public Authority verifyCredentials(Authentication authentication,
			Authority authority, String username, String password) {
		Authorities authorities;
		Users user = userService.getUserByUserName(username);
		if (user == null) {
			// LDAP authentication
			verifyLdapAuthentication(authentication);
			// Create user
			userService.createUser(authority, username,null);
		} else {
			if (StringUtils.isEmpty(user.getPassword())) {
				// LDAP authentication
				verifyLdapAuthentication(authentication);
			} else {
				// Database authentication
				StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
				if (!passwordEncoder.matches(password, user.getPassword())) {
					throw new BadCredentialsException("Empty credentials.");
				}
			}
			authorities = authoritiesService.getAuthorityByUsername(username);
			authority = authorities.getAuthority();
		}
		return authority;
	}

	/**
	 * Method that verifies ldap authentication
	 * @param authentication -	the authentication
	 */
	private void verifyLdapAuthentication(Authentication authentication) {
		Authentication ldapAuth = ldapAuthenticationProvider
				.authenticate(authentication);
		LdapUserDetails ldapUserDetails = (LdapUserDetails) ldapAuth
				.getPrincipal();

		// check correct authentication
		if (!ldapAuth.isAuthenticated() || !ldapUserDetails.isEnabled()
				|| !ldapUserDetails.isAccountNonExpired()
				|| !ldapUserDetails.isAccountNonLocked()) {
			throw new BadCredentialsException("Incorrect credentials.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication);
	}
}