package com.pentalog.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.User;
import com.pentalog.sc.model.WrapperUser;
import com.pentalog.sc.service.UserService;

@Controller
@RequestMapping(value = "/resources/register")
public class RegisterController {

    /**
     * The user service - contains methods used to fetch data regarding users
     * from the DAO layer.
     */
    @Autowired
    private UserService userService;

    /**
     * Register a new user
     * 
     * @param wrapper
     *            user that contains username and password
     * @return the new user
     */
    @Secured({ "ROLE_ADMIN" })
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody User register(@RequestBody WrapperUser wUser) {
        return userService.createUser(wUser);
    }
}
