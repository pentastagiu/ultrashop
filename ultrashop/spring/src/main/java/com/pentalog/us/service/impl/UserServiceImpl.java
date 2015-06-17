package com.pentalog.us.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pentalog.us.dao.UserDAO;
import com.pentalog.us.model.User;
import com.pentalog.us.service.UserService;

/**
 * The user service implementation
 * @author acozma and bpopovici
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	/**
	 * The user data access object 
	 */
	@Autowired
	private UserDAO userDAO;

	/**
	 * @see {@link UserService.getUsers}
	 */
	@Override
	public List<User> getUsers() {
		return userDAO.findAll();
	}

	/**
	 * @see {@link UserService.getUserById}
	 */
	@Override
	public User getUserById(int id) {
		return userDAO.findOne(id);
	}

	/**
	 * @see {@link UserService.postUser}
	 */
	@Override
	public void postUser(User user) {
		userDAO.save(user);
	}

	/**
	 * @see {@link UserService.putUser}
	 */
	@Override
	public void putUser(User user) {
		StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.save(user);
	}

	/**
	 * @see {@link UserService.deleteUser}
	 */
	@Override
	public void deleteUser(User user) {
		userDAO.delete(user);
	}

	@Override
	public User getUserByEmail(User user) {
		User u =  userDAO.findUserByEmail(user.getEmail());
		if(u != null) {
			if(user.getPassword() != null) {
				StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
				if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
					u.setPassword("");
				}
			}
			else {
				u.setPassword("");
			}
		}
		return u;
	}
}