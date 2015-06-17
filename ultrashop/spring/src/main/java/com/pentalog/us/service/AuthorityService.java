package com.pentalog.us.service;

import java.util.List;
import com.pentalog.us.model.Authority;

/**
 * The authorities service
 * @authors acozma and bpopovici
 *
 */
public interface AuthorityService {

	/**
	 * Method that get authorities
	 * @return
	 */
	List<Authority> getAuthorities();
	
	/**
	 * Method that get authority by id
	 * @param id
	 * @return
	 */
	Authority getAuthorityById(int id);
	
	/**
	 * Method that post authority
	 * @param authority
	 */
	void postAuthority(Authority authority);
	
	/**
	 * Method that put authority 
	 * @param authority
	 * @return
	 */
	void putAuthority(Authority authority);
	
	/**
	 * Method that delete authority
	 * @param authority
	 */
	void deleteAuthority(Authority authority);
}