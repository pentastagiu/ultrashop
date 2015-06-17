package com.pentalog.us.model;

import java.util.List;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import com.pentalog.us.util.PropertyMapDeserializer;

/**
 * The delivery stock java database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="deliverystock")
public class DeliveryStock {
	
	/**
	 * Delivery stock id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Delivery stock product guarantee
	 */
	private List<ProductGuarantee> productGuarantee;
	
	/**
	 * Delivery stock products
	 */
	@NotNull
	@ElementCollection
	private Map<Product,Integer> products;
	
	/**
	 * Delivery stock order
	 */
	@NotNull
	private Order order;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ProductGuarantee> getProductGuarantee() {
		return productGuarantee;
	}

	public void setProductGuarantee(List<ProductGuarantee> productGuarantee) {
		this.productGuarantee = productGuarantee;
	}

	@JsonDeserialize(using = PropertyMapDeserializer.class) 
	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}