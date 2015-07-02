package com.pentalog.sc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.sc.model.Authorities;
import com.pentalog.sc.model.Authorities.Authority;

/**
 * The authority data acces layer
 *
 */
public interface AuthoritiesDAO extends JpaRepository<Authorities, Integer> {
    /**
     * Method that finds an authority for an username
     * 
     * @param username
     *            - the username
     * @return the authority
     */
    Authorities findByUsername(String username);

    /**
     * Return a list of users with the authority received.
     * 
     * @param authority
     * @return
     */
    List<Authorities> findByAuthority(Authority authority);
    
}
