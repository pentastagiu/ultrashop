package com.pentalog.us.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The product feature java database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="productfeature")
public class ProductFeature {
	
	/**
	 * Product feature id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Product
	 */
	@NotNull
	private Product product;
	
	/**
	 * Feature
	 */
	@NotNull
	private Feature feature;
	
	/**
	 * Product feature value
	 */
	@NotNull
	private String value;
	
	@NotNull
	private Integer priority;

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

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}