package com.pentalog.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.Authorities;
import com.pentalog.sc.service.AuthoritiesService;
import com.pentalog.sc.service.UserService;

/**
 * 
 * The controller for authorities
 */
@Controller
@RequestMapping("/resources/authorities")
public class AuthoritiesController {

    @Autowired
    AuthoritiesService authoritiesService;

    @Autowired
    UserService userService;

    /**
     * Gets authorities for a user
     * 
     * @param username
     *            for user
     * @return the authorities
     */
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public @ResponseBody Authorities getAuthorityByUsername(
            @PathVariable String username) {
        return authoritiesService.getAuthorityByUsername(username);
    }

    /**
     * Gets all authorities
     * 
     * @return list of authorities
     */
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Authorities> getAuthorities() {
        return authoritiesService.getAuthorities();
    }

    /**
     * Updates authorities
     * 
     * @param authorities
     * @return new authorities
     */
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Authorities updateAuthorities(
            @RequestBody Authorities authorities) {
        return authoritiesService.updateAuthorities(authorities);
    }

    /**
     * Add new authorities in database
     * 
     * @param authorities
     * @return new authorities
     */
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Authorities createAuthorities(
            @RequestBody Authorities authorities) {
        return authoritiesService.createAuthority(authorities);
    }

    /**
     * Return a string telling if the user has admin role.
     */
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(value="/isAdmin",method = RequestMethod.POST)
    public @ResponseBody Authorities isAdmin(
            @RequestBody String token) {
	    return  authoritiesService.getAuthorityByUsername(userService.getUserByToken(token).getUsername());

        
    }
}
