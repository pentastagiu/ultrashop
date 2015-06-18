package com.pentalog.sr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pentalog.sr.model.Users;
import com.pentalog.sr.model.Wrapper;
import com.pentalog.sr.service.AuthoritiesService;
import com.pentalog.sr.service.UserService;

/**
 * Controller for web  services regarding user operations
 * @authors acozma and bpopovici
 *
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

	/**
	 *  The user service - contains methods used to fetch data regarding users from the DAO layer.
	 */
	@Autowired
	private UserService userService;
	
	/**
	 *  The authorities service - contains methods used to fetch data regarding the user authority from the DAO layer.
	 */
	@Autowired
	private AuthoritiesService authoritiesService;

	/**
	 * Web service that persists an user in the database, using the service provided by userService.
	 * @param obejcts -	The user and authority that will be saved in the database
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	Users registerUser(@RequestBody Wrapper<Object> objects) {
		return userService.registerUser(objects);
	}

	/**
	 * Web service that persists an user in the database, that tries to obtain an authentication token for web service usage, using the service
	 * provided by userService.
	 * @param user -	The user that will be saved in the database.
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.PUT)
	public @ResponseBody
	Users registerUser(@RequestBody Users user) {
		return userService.registerWsUser(user);
	}
	
	/**
	 * Web service that verifies if the user is in the database, and if so it is given an authentication token for web service usage, using the service
	 * provided by userService.
	 * @param user -	The user that will be logged.
	 * @return
	 */
	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public @ResponseBody
	String loginUser(@RequestBody Users user) {
		return userService.authenticate(user);
	}
}