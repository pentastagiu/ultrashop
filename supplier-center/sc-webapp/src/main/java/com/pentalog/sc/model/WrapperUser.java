package com.pentalog.sc.model;

import org.springframework.stereotype.Component;

/**
 * Class used to help 
 */
@Component
public class WrapperUser {
	/**
	 * the username for user
	 */
	private String username;

	/**
	 * the password inserted
	 */
	private String password;

	public WrapperUser() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
