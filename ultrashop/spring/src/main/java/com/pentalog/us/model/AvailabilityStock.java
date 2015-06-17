package com.pentalog.us.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The availability stock database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="availabilitystock")
public class AvailabilityStock {
	
	/**
	 * Availability stock id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Availability stock
	 */
	@NotNull
	private int stock;
	
	/**
	 * Availability stock product
	 */
	@NotNull
	private Product product;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}