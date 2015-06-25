package com.pentalog.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.Authorities;
import com.pentalog.sc.service.AuthoritiesService;

/**
 * 
 * The controller for authorities
 */
@Controller
@RequestMapping("/authorities")
public class AuthoritiesController {

	@Autowired
	AuthoritiesService authoritiesService;

	/**
	 * Gets authorities for a user
	 * 
	 * @param username
	 *            for user
	 * @return the authorities
	 */
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
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody Authorities createAuthorities(
			@RequestBody Authorities authorities) {
		return authoritiesService.createAuthority(authorities);
	}

}
