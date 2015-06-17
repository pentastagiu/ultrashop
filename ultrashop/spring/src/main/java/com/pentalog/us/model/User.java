package com.pentalog.us.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The user java database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="users")
public class User {

	/**
	 * User id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * User email
	 */
	@NotNull
	@Column(unique=true)
	private String email;
	
	/**
	 * User password
	 */
	@NotNull
	private String password;
	
	/**
	 * User firstname
	 */
	@NotNull
	private String firstname;
	
	/**
	 * User lastname
	 */
	@NotNull
	private String lastname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firsname) {
		this.firstname = firsname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}