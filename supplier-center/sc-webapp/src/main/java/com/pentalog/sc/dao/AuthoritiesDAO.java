package com.pentalog.sc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.sc.model.Authorities;

/**
 * The authority data acces layer
 *
 */
public interface AuthoritiesDAO extends JpaRepository<Authorities, Integer> {
	/**
	 * Method that finds an authority for an username
	 * 
	 * @param username
	 *            - the username
	 * @return the authority
	 */
	Authorities findByUsername(String username);
}
