package com.pentalog.us.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The product presentation java database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="productpresentation")
public class ProductPresentation {
	
	/**
	 * Product presentation id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Product description
	 */
	@NotNull
	private String description;
	
	/**
	 * Product presentation title
	 */
	@NotNull
	private String title;
	
	/**
	 * Product presentation section
	 */
	@NotNull
	private Integer section;
	
	/**
	 * Product presentation image source
	 */
	@NotNull
	private String imageSrc;
	
	/**
	 * Product
	 */
	@NotNull
	private Product product;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}