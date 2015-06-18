package com.pentalog.us.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.us.dao.CustomerDAO;
import com.pentalog.us.model.Customer;
import com.pentalog.us.model.User;
import com.pentalog.us.service.CustomerService;

/**
 * The customer service implementation
 * @author acozma and bpopovici
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	/**
	 * The customer data access object 
	 */
	@Autowired
	private CustomerDAO customerDAO;
	
	/**
	 * @see {@link CustomerService.getCustomers}
	 */
	@Override
	public List<Customer> getCustomers() {
		return customerDAO.findAll();
	}

	/**
	 * @see {@link CustomerService.getCustomerById}
	 */
	@Override
	public Customer getCustomerById(int id) {
		return customerDAO.findOne(id);
	}

	/**
	 * @see {@link CustomerService.postCustomer}
	 */
	@Override
	public void postCustomer(Customer customer) {
		customerDAO.save(customer);
	}

	/**
	 * @see {@link CustomerService.putCustomer}
	 */
	@Override
	public Customer putCustomer(Customer customer) {
		return customerDAO.save(customer);
	}

	/**
	 * @see {@link CustomerService.deleteCustomer}
	 */
	@Override
	public void deleteCustomer(Customer customer) {
		customerDAO.delete(customer);
	}

	@Override
	public Customer getCustomerByUser(User user) {
		return customerDAO.findCustomerByUser_IdAndEmailAndFirstnameAndLastname(user.getId(), user.getEmail(), user.getFirstname(), user.getLastname());
	}

	@Override
	public Customer getCustomerByCustomer(Customer customer) {
		return customerDAO.findCustomerByEmailAndFirstnameAndLastname(customer.getEmail(), customer.getFirstname(), customer.getLastname());
	}
}