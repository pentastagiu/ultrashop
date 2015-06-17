package com.pentalog.sr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.UserDAO;
import com.pentalog.sr.model.Authorities;
import com.pentalog.sr.model.Users;
import com.pentalog.sr.model.Wrapper;
import com.pentalog.sr.model.Authorities.Authority;
import com.pentalog.sr.service.AuthoritiesService;
import com.pentalog.sr.service.UserService;
import com.pentalog.sr.utils.AuthenticationUserDetails;
import com.pentalog.sr.utils.TokenUtils;

/**
 * The user service implementation
 * 
 * @authors acozma and bpopovici
 * 
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	/**
	 * The user data object
	 */
	@Autowired
	private UserDAO userDAO;

	/**
	 * The authority service
	 */
	@Autowired
	private AuthoritiesService authoritiesService;

	/**
	 * @see {@link UserService.registerUser}
	 */
	@Override
	public Users registerUser(Wrapper<Object> objects) {
		Users user = (Users) objects.getList().get(0);
		Authorities authorities = (Authorities) objects.getList().get(1);
		StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		authorities.setUsername(user.getUsername());
		authoritiesService.registerAuthorities(authorities);
		return userDAO.save(user);
	}

	/**
	 * @see {@link UserService.getUserByUserName}
	 */
	@Override
	public Users getUserByUserName(String userName) {
		return userDAO.findByUsername(userName);
	}

	/**
	 * @see {@link UserService.registerUser}
	 */
	@Override
	public Users registerUser(List<Object> objects) {
		Users user = (Users) objects.get(0);
		Authorities authorities = (Authorities) objects.get(1);
		authorities.setUsername(user.getUsername());
		authoritiesService.registerAuthorities(authorities);
		return userDAO.save(user);
	}

	/**
	 * @see {@link UserService.registerUserByEmail}
	 */
	@Override
	public Users registerUserByEmail(String email) {
		Users user = new Users();
		Authorities authorities = new Authorities();
		user.setEnabled(1);
		user.setUsername(email);
		authorities.setUsername(email);
		authorities.setAuthority(Authority.USER);
		List<Object> objects = new ArrayList<>();
		objects.add(user);
		objects.add(authorities);
		registerUser(objects);
		return user;
	}

	/**
	 * @see {@link UserService.getUserNameFromAuthentication}
	 */
	@Override
	public String getUserNameFromAuthentication() {
		String userName = null;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Map<String, String> atributes = new HashMap<>();
		if (auth.getName().contains("@gmail")) {
			OpenIDAuthenticationToken openIdAuth = (OpenIDAuthenticationToken) auth;
			for (OpenIDAttribute oId : openIdAuth.getAttributes()) {
				atributes.put(oId.getName(), oId.getValues().get(0));
			}
			userName = atributes.get("firstname") + " "
					+ atributes.get("lastname");
		} else {
			userName = auth.getName();
		}
		return userName;
	}

	/**
	 * @see {@link UserService.createUser}
	 */
	@Override
	public void createUser(Authority authority, String username, String password) {
		Authorities authorities;
		List<Object> objects = new ArrayList<>();
		Users newUser = new Users();
		newUser.setEnabled(1);
		newUser.setUsername(username);
		newUser.setPassword(password);
		authorities = new Authorities();
		authorities.setUsername(username);
		authorities.setAuthority(authority);
		objects.add(newUser);
		objects.add(authorities);
		registerUser(objects);
	}

	/**
	 * @see {@link UserService.registerWsUser}
	 */
	@Override
	public Users registerWsUser(Users user) {
		Authorities authorities = null;
		StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		if (getUserByUserName(user.getUsername()) == null) {
			user.setEnabled(1);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			authorities = new Authorities();
			authorities.setAuthority(Authority.OPERATOR);
			List<Object> objects = new ArrayList<>();
			objects.add(user);
			objects.add(authorities);
			registerUser(objects);
			return user;
		}
		return null;
	}

	/**
	 * @see {@link UserService.authenticate}
	 */
	@Override
	public String authenticate(Users user) {
		Authorities authorities = null;
		AuthenticationUserDetails userDetails = null;
		Users dbUser = getUserByUserName(user.getUsername());
		if(dbUser==null) return null;
		StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
			return null;
		}
		authorities = authoritiesService.getAuthorityByUsername(dbUser
				.getUsername());
		userDetails = new AuthenticationUserDetails(dbUser, authorities);

		return TokenUtils.createToken(userDetails);
	}
}