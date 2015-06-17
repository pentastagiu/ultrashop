package com.pentalog.us.service;

import java.util.List;
import com.pentalog.us.model.User;

/**
 * The user service
 * @authors acozma and bpopovici
 *
 */
public interface UserService {
	
	/**
	 * Method that get users
	 * @return
	 */
	List<User> getUsers();
	
	/**
	 * Method that get user by id
	 * @param id
	 * @return
	 */
	User getUserById(int id);
	
	User getUserByEmail(User user);
	
	/**
	 * Method that post user
	 * @param user
	 */
	void postUser(User user);
	
	/**
	 * Method that put user
	 * @param user
	 */
	void putUser(User user);
	
	/**
	 * Method that delete user
	 * @param user
	 */
	void deleteUser(User user);
}