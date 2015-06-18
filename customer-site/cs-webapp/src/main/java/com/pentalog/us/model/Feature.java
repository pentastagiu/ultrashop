package com.pentalog.us.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The feature java database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="feature")
public class Feature {
	
	/**
	 * Feature id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Feature name
	 */
	@NotNull
	private String name;
	
	/**
	 * Feature parent
	 */
	private Integer parent;
	
	private Integer priority;

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

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}