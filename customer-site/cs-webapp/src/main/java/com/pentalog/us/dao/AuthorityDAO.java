package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.Authority;

/**
 * The authority data access layer
 * @authors acozma and bpopovici
 *
 */
public interface AuthorityDAO extends JpaRepository<Authority, Integer> {
}