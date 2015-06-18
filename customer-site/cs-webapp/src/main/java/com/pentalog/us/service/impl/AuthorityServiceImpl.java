package com.pentalog.us.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.us.dao.AuthorityDAO;
import com.pentalog.us.model.Authority;
import com.pentalog.us.service.AuthorityService;

/**
 * The authority service implementation
 * @author acozma and bpopovici
 *
 */
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
	
	/**
	 * The authority data access object 
	 */
	@Autowired
	private AuthorityDAO authorityDAO;

	/**
	 * @see {@link AuthorityService.getAuthorities}
	 */
	@Override
	public List<Authority> getAuthorities() {
		return authorityDAO.findAll();
	}

	/**
	 * @see {@link AuthorityService.getAuthorityById}
	 */
	@Override
	public Authority getAuthorityById(int id) {
		return authorityDAO.findOne(id);
	}

	/**
	 * @see {@link AuthorityService.postAuthority}
	 */
	@Override
	public void postAuthority(Authority authority) {
		authorityDAO.save(authority);
	}

	/**
	 * @see {@link AuthorityService.putAuthority}
	 */
	@Override
	public void putAuthority(Authority authority) {
		authorityDAO.save(authority);
	}

	/**
	 * @see {@link AuthorityService.deleteAuthority}
	 */
	@Override
	public void deleteAuthority(Authority authority) {
		authorityDAO.delete(authority);
	}
}