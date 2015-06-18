package com.pentalog.sr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The users database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name = "users")
public class Users {
	
	/**
	 * The user id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * The username
	 */
	@NotNull
	@Column(unique=true)
	private String username;
	
	/**
	 * The password
	 */
	private String password;
	
	/**
	 * The enabled feature - indicates if an username is still active
	 */
	@NotNull
	private Integer enabled;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}