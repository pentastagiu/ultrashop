package com.pentalog.sc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.pentalog.sc.dao.UserDAO;
import com.pentalog.sc.model.Authorities;
import com.pentalog.sc.model.Authorities.Authority;
import com.pentalog.sc.model.User;
import com.pentalog.sc.util.Md5Util;

/**
 * implementation of userService interface
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	/**
	 * The user dao
	 */
	@Autowired
	private UserDAO userDao;

	/**
	 * The authority service
	 */
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	Md5Util md5Util;

	/**
	 * @see {@link userService.getUserByUsername}
	 */
	@Override
	public User getUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

	/**
	 * @see {@link userService.getUserByToken}
	 */
	@Override
	public User getUserByToken(String token) {
		return userDao.findByToken(token);
	}

	/**
	 * @see {@link UserService.createUser}
	 */
	@Override
	public void createUser(Authority authority, String username, String password) {
		Authorities authorities;
		List<Object> objects = new ArrayList<>();
		String token = "", userToken = "";
		String userSalt = generateUserSalt();
		String applicationSalt = "";
		try {
			applicationSalt = md5Util.readAppSalt();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		User newUser = new User();
		newUser.setUsername(username);
		token = username.concat(password).concat(userSalt)
				.concat(applicationSalt);
		try {
			userToken = md5Util.generateMd5(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		newUser.setToken(userToken);
		newUser.setUsersalt(userSalt);

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
	public User registerUser(List<Object> objects) {
		User user = (User) objects.get(0);
		Authorities authorities = (Authorities) objects.get(1);
		authorities.setUsername(user.getUsername());
		authoritiesService.createAuthority(authorities);
		return userDao.save(user);
	}

	/**
	 * @see {@link UserService.authenticate}
	 */
	public String authenticate(String username, String password)
			throws AuthenticationException {
		String applicationSalt = "", runtimeToken = "";
		User user = userDao.findByUsername(username);
		try {
			applicationSalt = md5Util.readAppSalt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String token = username.concat(password).concat(user.getUsersalt())
				.concat(applicationSalt);
		try {
			runtimeToken = md5Util.generateMd5(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (runtimeToken.equals(user.getToken())) {
			return runtimeToken;
		}
		return "";
	}

	/**
	 * generates an userSalt (random)
	 * 
	 * @return the usersalt
	 */
	private String generateUserSalt() {
		StringBuilder userSalt = new StringBuilder();
		Random randomGenerator = new Random();
		for (int idx = 1; idx <= 10; ++idx) {
			int randomInt = randomGenerator.nextInt(9);
			userSalt.append(randomInt);
		}
		return userSalt.toString();
	}

	/**
	 * @see {@link UserService.updateUser}
	 */
	@Override
	public User updateUser(User user) {
		User userToUpdate = userDao.findOne(user.getId());
		if (userToUpdate != null) {
			userToUpdate.setUsername(user.getUsername());
			userToUpdate.setToken(user.getToken());
		}
		return userToUpdate;
	}

}
