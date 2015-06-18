package com.pentalog.sr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.sr.dao.CustomerDAO;
import com.pentalog.sr.model.Customer;
import com.pentalog.sr.service.CustomerService;

/**
 * The customer service implementation
 * @authors acozma and bpopovici
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	/**
	 * The customer data access object
	 */
	@Autowired
	private CustomerDAO customerDAO;
	
	/**
	 * @see {@link CustomerService.getCustomerById}
	 */
	@Override
	public Customer getCustomerById(int id) {
		return customerDAO.findOne(id);
	}

	@Override
	public Customer registerCustomer(Customer customer) {
		return customerDAO.save(customer);
	}
}