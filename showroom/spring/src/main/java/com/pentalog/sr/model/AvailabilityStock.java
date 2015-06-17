package com.pentalog.sr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The availabilitystock database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name = "availabilitystock")
public class AvailabilityStock {
	
	/**
	 * The availability stock id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * The product with the current availability stock 
	 */
	@OneToOne
	private Product product;
	
	/**
	 * The stock quantity
	 */
	@NotNull
	private int stock;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}