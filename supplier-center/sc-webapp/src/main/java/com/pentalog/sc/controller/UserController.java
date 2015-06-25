package com.pentalog.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.User;
import com.pentalog.sc.service.AuthoritiesService;
import com.pentalog.sc.service.UserService;

/**
 * Controller for web services regarding user operations
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

	/**
	 * The user service - contains methods used to fetch data regarding users
	 * from the DAO layer.
	 */
	@Autowired
	private UserService userService;

	/**
	 * The authorities service - contains methods used to fetch data regarding
	 * the user authority from the DAO layer.
	 */
	@Autowired
	private AuthoritiesService authoritiesService;

	/**
	 * Gets a user by his username
	 * 
	 * @param username
	 * @return the username
	 */
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public @ResponseBody User getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}

	/**
	 * Verify the username and password given for login
	 * 
	 * @param the
	 *            string that contains the username and password
	 * @return the token of username (if success) else return "error"
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestBody String usernameAndPassword) {
		return userService.authenticate(usernameAndPassword);
	}
}