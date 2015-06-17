package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.User;

/**
 * The user data access layer
 * @authors acozma and bpopovici
 *
 */
public interface UserDAO extends JpaRepository<User, Integer> {
	
	User findUserByEmail(String email);
}