package com.pentalog.sc.service;

import java.util.List;

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
	
	/**
	 * Get all authorities
	 * @return - list with authorities
	 */
	List<Authorities> getAuthorities();
	
	/**
	 * updates an authority
	 * @return - the new authority
	 */
	Authorities updateAuthorities(Authorities authorities);
}
