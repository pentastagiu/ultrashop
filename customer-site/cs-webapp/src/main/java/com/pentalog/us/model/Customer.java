package com.pentalog.us.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The customer.java database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="customer")
public class Customer {

	/**
	 * Customer id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/** 
	 * Customer first name
	 */
	@NotNull
	private String firstname;
	
	/**
	 * Customer last name
	 */
	@NotNull
	private String lastname;
	
	/**
	 * Customer email
	 */
	@NotNull
	private String email;
	
	/**
	 * Customer phone
	 */
	@NotNull
	private String phone;
	
	/**
	 * Customer address
	 */
	@NotNull
	private String address;
	
	/**
	 * Customer user
	 */
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}