package com.pentalog.us.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The product java database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="product")
public class Product {
	
	/**
	 * Product id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Product name
	 */
	@NotNull
	private String name;
	
	/**
	 * Product price
	 */
	@NotNull
	private double price;
	
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
}