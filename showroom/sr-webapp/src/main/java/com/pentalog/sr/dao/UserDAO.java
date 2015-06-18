package com.pentalog.sr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sr.model.Users;

/**
 * The user data access layer
 * @authors acozma and bpopovici
 *
 */
public interface UserDAO extends JpaRepository<Users, Integer>{
	/**
	 * Method that returns an user by its username
	 * @param username -	the username
	 * @return
	 */
	Users findByUsername(String username);
}