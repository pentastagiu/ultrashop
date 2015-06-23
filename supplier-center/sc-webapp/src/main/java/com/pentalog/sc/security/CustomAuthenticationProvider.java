package com.pentalog.sc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.pentalog.sc.model.Authorities.Authority;
import com.pentalog.sc.service.AuthoritiesService;
import com.pentalog.sc.service.UserService;

/**
 * The LDAP and database authentication provider class
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    /**
     * The user service
     */
    @Autowired
    private UserService userService;

    /**
     * The authority service
     */
    @Autowired
    private AuthoritiesService authoritiesService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        if (authentication.getAuthorities() == null) {
            return null;
        }
        return authentication;
    }

    public Authority verifyCredentials(Authentication authentication,
            Authority authority, String username, String password) {
     
        return authority;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication);
    }

}