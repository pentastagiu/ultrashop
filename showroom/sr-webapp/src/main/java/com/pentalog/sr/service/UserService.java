package com.pentalog.sr.service;

import java.util.List;

import com.pentalog.sr.model.Authorities.Authority;
import com.pentalog.sr.model.Users;
import com.pentalog.sr.model.Wrapper;

/**
 * The user service
 * @authors acozma and bpopovici
 *
 */
public interface UserService {
	
	/**
	 * Method that registers an user with its authority in the database
	 * @param objects - wrapper that contains user details and its authority
	 * @return
	 */
	Users registerUser(Wrapper<Object> objects);
	
	/**
	 * Method that registers an user with its authority in the database
	 * @param objects - list that contains user details and its authority
	 * @return
	 */
	Users registerUser(List<Object> objects);
	
	/**
	 * Method that returns the user with the given username
	 * @param userName -	the username
	 * @return
	 */
	Users getUserByUserName(String userName);
	
	/**
	 * Method that registers an user in the database with the email provided. The user will have a null password
	 * @param email -	the provided email
	 * @return
	 */
	Users registerUserByEmail(String email);
	
	/**
	 * Method that registers an users via a web service sign up
	 * @param user -	the user
	 * @return
	 */
	Users registerWsUser(Users user);
	
	/**
	 * Method that returns the username for an user who tries to login with the Google mail account
	 * @return
	 */
	String getUserNameFromAuthentication();

	/**
	 * Method that creates an user with the given credentials and authority
	 * @param authority
	 * @param username
	 * @param password
	 */
	void createUser(Authority authority, String username, String password);

	/**
	 * Method that authenticates an existing user and returns the token
	 * @param user - The user that is authenticated
	 * @return
	 */
	String authenticate(Users user);
}