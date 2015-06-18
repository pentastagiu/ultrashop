package com.pentalog.sr.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import com.pentalog.sr.model.Product;

/**
 * The product description database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name = "productDescription")
public class ProductDescription {
	/**
	 * The product description id
	 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * The product description summary
	 */
	@NotNull
	private String description;
	
	/**
	 * The product with the current description
	 */
	@OneToOne
	private Product product;
	
	/**
	 * The product description timestamp -	used for monitoring updates on table
	 */
	private Timestamp timeStamp;
	
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