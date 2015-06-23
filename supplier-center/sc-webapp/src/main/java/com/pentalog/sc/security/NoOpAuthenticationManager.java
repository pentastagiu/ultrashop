package com.pentalog.sc.security;

import org.springframework.security.authentication.AuthenticationManager;  
import org.springframework.security.core.Authentication;  
import org.springframework.security.core.AuthenticationException;  
  
public class NoOpAuthenticationManager implements AuthenticationManager {  
     
	/**
	 * Method that throws authentication exception upon an unsuccessful authentication
	 */
    @Override  
    public Authentication authenticate(Authentication authentication)  
            throws AuthenticationException {  
        return authentication;  
    }  
}  