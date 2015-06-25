package com.pentalog.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.User;
import com.pentalog.sc.model.WrapperUser;
import com.pentalog.sc.service.AuthoritiesService;
import com.pentalog.sc.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

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
     * @param wrapper
     *            user
     * @return the token of username (if success) else return "error"
     */
    @RequestMapping( method = RequestMethod.POST)
    public @ResponseBody String login(@RequestBody WrapperUser wUser) {
        return userService.authenticate(wUser);
    }

    /**
     * Register a new user
     * 
     * @param wrapper
     *            user that contains username and password
     * @return the new user
     */
    @RequestMapping( method = RequestMethod.PUT)
    public @ResponseBody User register (@RequestBody WrapperUser wUser){
        return userService.createUser(wUser);
    }
}
