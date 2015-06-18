package com.pentalog.us.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The card database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="card")
public class Card {

	/**
	 * Card id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/** 
	 * Card serial
	 */
	@NotNull
	private String cardnumber;
	
	/**
	 * Card name
	 */
	@NotNull
	private String cardname;
	
	/**
	 * Card number
	 */
	@NotNull
	private String cardcod;
	
	/**
	 * Card expiration
	 */
	@NotNull
	private String cardexpire;
	
	/**
	 * Card user
	 */
	@NotNull
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardcod() {
		return cardcod;
	}

	public void setCardcod(String cardcod) {
		this.cardcod = cardcod;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getCardexpire() {
		return cardexpire;
	}

	public void setCardexpire(String cardexpire) {
		this.cardexpire = cardexpire;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}