package com.pentalog.sc.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.pentalog.sc.dao.UserDAO;
import com.pentalog.sc.model.Authorities;
import com.pentalog.sc.model.Authorities.Authority;
import com.pentalog.sc.model.User;
import com.pentalog.sc.model.WrapperUser;
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
	public User createUser(WrapperUser wUser) {
		String token = "", userToken = "", username="", password="";
		String userSalt = generateUserSalt();
		User newUser = new User();
		
		username = wUser.getUsername();
		password = wUser.getPassword();
		
		newUser.setUsername(username);
		token = username.concat(password).concat(userSalt);
		try {
			userToken = md5Util.generateMd5(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		newUser.setToken(userToken);
		newUser.setUsersalt(userSalt);

		return registerUser(newUser);
	}

	/**
	 * @see {@link UserService.registerWsUser}
	 */
	@Override
	public User registerUser(User user) {
		Authorities authorities = new Authorities();
		authorities.setUsername(user.getUsername());
		authorities.setAuthority(Authority.OPERATOR);
		
		authoritiesService.createAuthority(authorities);
		return userDao.save(user);
	}

	/**
	 * @see {@link UserService.authenticate}
	 */
	public String authenticate(WrapperUser wUser)
			throws AuthenticationException {
		String username = wUser.getUsername();
		String password = wUser.getPassword();
		
		String runtimeToken = "";
		User user = getUserByUsername(username);

		String token = username.concat(password).concat(user.getUsersalt());
		try {
			runtimeToken = md5Util.generateMd5(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (runtimeToken.equals(user.getToken())) {
			return runtimeToken;
		}
		return "error";
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
