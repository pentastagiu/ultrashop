package com.pentalog.sr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The product imagery database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name = "productimagery")
public class ProductImagery {

	/**
	 * The product imagery id
	 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * The product with the current product imagery
	 */
	@ManyToOne
	private Product product;

	/**
	 * Total number of product images
	 */
	@NotNull
	private Integer totalImages;

	public Integer getTotalImages() {
		return totalImages;
	}

	public void setTotalImages(Integer totalImages) {
		this.totalImages = totalImages;
	}

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
}