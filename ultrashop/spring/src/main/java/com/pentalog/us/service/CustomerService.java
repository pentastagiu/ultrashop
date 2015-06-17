package com.pentalog.us.service;

import java.util.List;

import com.pentalog.us.model.Customer;
import com.pentalog.us.model.User;

/**
 * The customer service
 * @authors acozma and bpopovici
 *
 */
public interface CustomerService {
	
	/**
	 * Method that get customers
	 * @return
	 */
	List<Customer> getCustomers();
	
	/**
	 * Method that get customer by id
	 * @param id
	 * @return
	 */
	Customer getCustomerById(int id);
	
	/**
	 * Method that post customer
	 * @param customer
	 */
	void postCustomer(Customer customer);
	
	/**
	 * Method that put customer
	 * @param customer
	 */
	Customer putCustomer(Customer customer);
	
	/**
	 * Method that delete customer
	 * @param customer
	 */
	void deleteCustomer(Customer customer);
	
	Customer getCustomerByUser(User user);
	
	Customer getCustomerByCustomer(Customer customer);
}