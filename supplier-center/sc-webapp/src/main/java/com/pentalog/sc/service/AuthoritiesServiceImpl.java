package com.pentalog.sc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.sc.dao.AuthoritiesDAO;
import com.pentalog.sc.model.Authorities;

/**
 * The implementation of AuthoritiesService
 */
@Service("authoritiesService")
public class AuthoritiesServiceImpl implements AuthoritiesService {
	/**
	 * The authorities dao
	 */
	@Autowired
	AuthoritiesDAO authoritiesDao;

	/**
	 * @see {@link authoritiesService.createAuthority}
	 */
	@Override
	public Authorities createAuthority(Authorities authority) {
		return authoritiesDao.save(authority);
	}
	
	/**
	 * @see {@link authoritiesService.getAuthorityByUsername}
	 */
	@Override
	public Authorities getAuthorityByUsername(String username) {
		return authoritiesDao.findByUsername(username);
	}

}
