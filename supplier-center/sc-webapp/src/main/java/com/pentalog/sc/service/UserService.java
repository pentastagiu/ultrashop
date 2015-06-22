package com.pentalog.sc.service;

import java.util.List;

import com.pentalog.sc.model.User;
import com.pentalog.sc.model.Authorities.Authority;

/**
 * The user service
 *
 */
public interface UserService {
	/**
	 * Finds an user by username
	 * 
	 * @param - the username
	 * @return - the user
	 */
	User getUserByUsername(String username);

	/**
	 * Finds an user by token
	 * 
	 * @param - the token
	 * @return the user
	 */
	User getUserByToken(String token);

	/**
	 * Updates an user
	 * 
	 * @param - the user
	 * @return - the new user
	 */
	public User updateUser(User user);

	/**
	 * register user
	 * 
	 * @param - the user
	 * @return - the user
	 */
	User registerUser(List<Object> objects);

	/**
	 * creates an user having username, password and authoritys
	 * 
	 * @param authority
	 * @param username
	 * @param password
	 */
	void createUser(Authority authority, String username, String password);

	/**
	 * 
	 * @param username
	 * @param password
	 * @return - the token
	 */
	String authenticate(String username, String password);

}
