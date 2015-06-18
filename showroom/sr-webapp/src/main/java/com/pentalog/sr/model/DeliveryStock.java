package com.pentalog.sr.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The delivery stock database model
 * @author acozma
 *
 */
@XmlRootElement
@Entity
@Table(name = "deliverystock")
public class DeliveryStock {
	
	/**
	 * The delivery stock id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * The product with the current delivery stock
	 */
	@OneToOne
	private Product product;
	
	/**
	 * The serial code for the delivery stock
	 */
	private String serial;
	
	/**
	 * The stock for the delivery stock
	 */
	@NotNull
	private int stock;
	
	/**
	 * The order for the delivery stock
	 */
	@ManyToOne
	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
	public String getSerial() {
		return serial;
	}

	public void setRandomSerial() {
		this.serial = UUID.randomUUID().toString();
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}