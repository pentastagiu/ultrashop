package com.pentalog.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
 * Controller for web  services regarding user operations
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
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value="/{username}",method= RequestMethod.GET)
	public @ResponseBody User getUserByUsername(@PathVariable String username){
		return userService.getUserByUsername(username);
	}
	
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method=RequestMethod.PUT)
	public @ResponseBody User registerUser (@RequestBody User user){
		User u = new User();
		u.setUsername("asdad");
		return u;
	}
}