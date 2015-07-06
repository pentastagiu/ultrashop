package com.pentalog.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.Authorities.Authority;
import com.pentalog.sc.model.User;
import com.pentalog.sc.model.WrapperUser;
import com.pentalog.sc.service.UserService;

/**
 * Controller for web services regarding user operations
 */
@Controller
@RequestMapping(value = "/resources/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Secured({ "ROLE_ADMIN" })
    @RequestMapping(value = "/operators", method = RequestMethod.GET)
    public @ResponseBody List<User> getOperators() {

        return userService.findByAuthority(Authority.OPERATOR);
    }

    @Secured({ "ROLE_ADMIN" })
    @RequestMapping(value = "/operators", method = RequestMethod.DELETE)
    public @ResponseBody User deleteOperator(@RequestBody User user) {

        return userService.deleteOperator(user);
    }
    
    @Secured({"ROLE_ADMIN","ROLE_OPERATOR"})
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public @ResponseBody User updateUser(@RequestBody WrapperUser wrapperUser){
        return userService.changePassword(wrapperUser);
    }
}