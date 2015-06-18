package com.pentalog.sr.service;

import com.pentalog.sr.model.Authorities;
/**
 * The authorities service
 * @authors acozma and bpopovici
 *
 */
public interface AuthoritiesService {
	
	/**
	 * Method that registers an authority 
	 * @param authority -  the authority
	 * @return
	 */
	Authorities registerAuthorities(Authorities authority);
	
	/**
	 * Method that fetches the authority from the database with the given username
	 * @param username -	the username
	 * @return
	 */
	Authorities getAuthorityByUsername(String username);
}