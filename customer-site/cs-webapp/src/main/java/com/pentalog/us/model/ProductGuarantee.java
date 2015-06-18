package com.pentalog.us.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The product guarantee java database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="Productguarantee")
public class ProductGuarantee {

	/**
	 * Product guarantee id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Product guarantee number of months
	 */
	@NotNull
	private Integer numberOfMonths;
	
	/**
	 * Product
	 */
	@NotNull
	private Product product;
	
	/**
	 * Product guarantee serial
	 */
	private String serial;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(Integer numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
}