package com.pentalog.sc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * The user database model
 */
@XmlRootElement
@Entity
@Table(name = "users")
public class User {
	/**
	 * User id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Integer id;

	/**
	 * The username
	 */
	@NotNull
	@Column(unique = true)
	private String username;

	/**
	 * The user token
	 */
	@NotNull
	@Column
	private String token;

	/**
	 * The usersalt
	 */
	@NotNull
	@Column
	private String usersalt;

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsersalt() {
		return usersalt;
	}

	public void setUsersalt(String usersalt) {
		this.usersalt = usersalt;
	}

}
