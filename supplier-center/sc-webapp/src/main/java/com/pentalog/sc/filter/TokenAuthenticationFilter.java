package com.pentalog.sc.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pentalog.sc.model.User;
import com.pentalog.sc.security.NoOpAuthenticationManager;
import com.pentalog.sc.security.TokenSimpleUrlAuthenticationSuccessHandler;
import com.pentalog.sc.service.AuthoritiesService;
import com.pentalog.sc.service.UserService;

public class TokenAuthenticationFilter extends
        AbstractAuthenticationProcessingFilter {

    @Autowired
    UserService userService;
    @Autowired
    AuthoritiesService authoritiesService;

    protected TokenAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl); // defaultFilterProcessesUrl -
                                          // specified in
                                          // applicationContext.xml.
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(
                defaultFilterProcessesUrl)); // Authentication will only be
                                             // initiated for the request url
                                             // matching this pattern
        setAuthenticationManager(new NoOpAuthenticationManager());
        setAuthenticationSuccessHandler(new TokenSimpleUrlAuthenticationSuccessHandler());
    }

    /**
     * Attempt to authenticate request
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException,
            IOException, ServletException {
        String token = request.getHeader("Authorization");
        logger.info("token found:" + token);

        AbstractAuthenticationToken userAuthenticationToken = authUserByToken(token);
        if (userAuthenticationToken == null)
            throw new AuthenticationServiceException("Invalid Token");
        return userAuthenticationToken;
    }

    /**
     * authenticate the user based on token
     * 
     * @return
     */
    private AbstractAuthenticationToken authUserByToken(String token) {
        if (token == null)
            return null;

        User user = userService.getUserByToken(token);
        if (user == null) {
            return null;
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(authoritiesService.getAuthorityByUsername(
                user.getUsername()).getAuthority());
        AbstractAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user, token, authorities);

        return authToken;

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}