package com.pentalog.sr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.AuthoritiesDAO;
import com.pentalog.sr.model.Authorities;
import com.pentalog.sr.service.AuthoritiesService;

/**
 * The authority service implementation
 * @author acozma
 *
 */
@Service("authoritiesService")
public class AuthoritiesServiceImpl implements AuthoritiesService{
	
	/**
	 * The authority data access object 
	 */
	@Autowired
	private AuthoritiesDAO authoritiesDAO;

	/**
	 * @see {@link AuthoritiesService.registerAuthorities}
	 */
	@Override
	public Authorities registerAuthorities(Authorities authorities) {
		return authoritiesDAO.save(authorities);
	}

	/**
	 * @see {@link AuthoritiesService.getAuthorityByUsername}
	 */
	@Override
	public Authorities getAuthorityByUsername(String username) {
		return authoritiesDAO.findByUsername(username);
	}
	
}