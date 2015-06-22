package com.pentalog.sc.service;

import com.pentalog.sc.model.Authorities;

/**
 * 
 * The authority service
 */
public interface AuthoritiesService {
	/**
	 * Creates an authority
	 * @param - the authority
	 * @return
	 */
	Authorities createAuthority(Authorities authority);
	
	/**
	 * Finds an authority for an username
	 * @param - the username
	 * @return - authorities
	 */
	Authorities getAuthorityByUsername(String username);
}
