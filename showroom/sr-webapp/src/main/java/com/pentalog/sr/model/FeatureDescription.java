package com.pentalog.sr.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import com.pentalog.sr.model.Product;

/**
 * The feature description database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name = "featureDescription")
public class FeatureDescription {

	/**
	 * The feature description id
	 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * The feature description
	 */
	@NotNull
	private String description;

	/**
	 * The product with the current feature description
	 */
	@ManyToOne
	private Product product;
	
	/**
	 * The feature description title
	 */
	@NotNull
	private String title;
	
	/**
	 * The section number for the current feature description 
	 */
	@NotNull
	private Integer sectionNumber;
	
	/**
	 * The image path
	 */
	private String imageURL;
	
	/**
	 * The timestamp -	used for monitoring updates on table
	 */
	private Timestamp timeStamp;

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Integer getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(Integer sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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