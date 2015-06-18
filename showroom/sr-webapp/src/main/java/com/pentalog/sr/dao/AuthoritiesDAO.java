package com.pentalog.sr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sr.model.Authorities;

/**
 * The authority data access layer
 * @authors acozma and bpopovici
 *
 */
public interface AuthoritiesDAO extends JpaRepository<Authorities, Integer>{
	/**
	 * Method that returns the authority for the given username.
	 * @param username  -	the username
	 * @return
	 */
	Authorities findByUsername(String username);
}