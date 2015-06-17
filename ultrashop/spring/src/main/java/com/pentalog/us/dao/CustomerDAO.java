package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.Customer;

/**
 * The customer data access layer
 * @authors acozma and bpopovici
 *
 */
public interface CustomerDAO extends JpaRepository<Customer, Integer> {
	
	Customer findCustomerByUser_IdAndEmailAndFirstnameAndLastname(int user_id, String email, String firstName, String lastName);
	
	Customer findCustomerByEmailAndFirstnameAndLastname(String email, String firstName, String lastName);
}