package com.pentalog.sr.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The product database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name = "product")
public class Product {
	/**
	 * The product id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * The product name
	 */
	private String name;
	
	/**
	 * The product price
	 */
	private double price;
	
	/**
	 * The product timestamp -	used for monitoring updates on table
	 */
	private Timestamp timeStamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
}