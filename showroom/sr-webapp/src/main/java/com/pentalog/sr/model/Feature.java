package com.pentalog.sr.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The feature database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name = "feature")
public class Feature {
	
	/**
	 * The feature id
	 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * The feature name
	 */
	@NotNull
	private String name;
	
	/**
	 * The feature value
	 */
	@NotNull
	private String value;

	/**
	 * The product with the current feature 
	 */
	@ManyToOne
	private Product product;
	
	/**
	 * Feature timeStamp -	used for monitoring updates on table
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
}