package com.pentalog.sr.utils;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.pentalog.sr.model.Authorities;
import com.pentalog.sr.model.Users;

/**
 * Class that retains user authentication credentials
 * @authors acozma and bpopovici
 *
 */
@SuppressWarnings("serial")
public class AuthenticationUserDetails implements UserDetails {

	/**
	 * The username
	 */
    private final String login;
    
    /**
     * The hashed password
     */
    private final String passwordHash;
    
    /**
     * User attribute used to verify user availability
     */
    private final boolean enabled;
    private HashSet<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

    public AuthenticationUserDetails(Users user, Authorities authorities) {
        this.login = user.getUsername();
        this.passwordHash = user.getPassword();
        this.enabled = true;
        this.grantedAuthorities.add(authorities.getAuthority());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getLogin() {
        return login;
    }
}