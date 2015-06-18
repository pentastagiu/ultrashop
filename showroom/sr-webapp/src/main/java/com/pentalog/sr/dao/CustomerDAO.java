package com.pentalog.sr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sr.model.Customer;

/**
 * The customer data access layer
 * @authors acozma and bpopovici
 *
 */
public interface CustomerDAO extends JpaRepository<Customer,Integer>{}
