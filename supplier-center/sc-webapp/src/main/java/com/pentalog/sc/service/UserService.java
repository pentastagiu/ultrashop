package com.pentalog.sc.service;

import com.pentalog.sc.model.User;
import com.pentalog.sc.model.WrapperUser;

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
	User registerUser(User user);

	/**
	 * creates an user having username, password and authoritys
	 * 
	 * @param authority
	 * @param wrapper
	 *            user that contains username and password
	 */
	User createUser(WrapperUser wUser);

	/**
	 * Authenticate user
	 * 
	 * @param wrapper
	 *            user that contains username and password
	 * @return - the token
	 */
	String authenticate(WrapperUser wUser);

}
