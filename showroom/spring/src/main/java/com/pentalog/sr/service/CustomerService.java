package com.pentalog.sr.service;

import com.pentalog.sr.model.Customer;

/**
 * The customer service
 * @author acozma
 *
 */
public interface CustomerService {
	/**
	 * Method that returns a customer from the database with the given id
	 * @param id -	the customer id
	 * @return
	 */
	Customer getCustomerById(int id);
	
	Customer registerCustomer(Customer customer);
}