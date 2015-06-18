package com.pentalog.sr.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import com.pentalog.sr.service.UserService;

/**
 * Login bean
 * @authors acozma and bpopovici
 *
 */
@ManagedBean
@RequestScoped
public class LoginBean {

	/**
	 *  The user service - contains methods used to fetch data regarding user information from the DAO layer.
	 */
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	
	String userName;

	@PostConstruct
	public void init() {
		userName = userService.getUserNameFromAuthentication();
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}