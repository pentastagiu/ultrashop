package com.pentalog.sc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sc.model.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	/**
	 * Finds an user by his username
	 * 
	 * @param username
	 * @return the user
	 */
	User findByUsername(String username);

	/**
	 * Finds an user by his token
	 * 
	 * @param token
	 * @return the user
	 */
	User findByToken(String token);
}
