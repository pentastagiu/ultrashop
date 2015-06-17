package com.pentalog.sr.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import com.pentalog.sr.converter.NotifyClientConverter;
import com.pentalog.sr.converter.StatusConverter;

/**
 * The orders database model
 * @author acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name = "orders")
public class Order {
	
	/**
	 * The order id
	 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * The order date
	 */
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date oDate;

	/**
	 * List of products on the order
	 */
	@OneToMany
	private Collection<Product> products;
	
	private List<ProductQuantity> productQuantity;
	
	/**
	 * The customer
	 */
	@ManyToOne
	private Customer customer;
	
	/**
	 * The order status
	 */
	@Convert(converter = StatusConverter.class)
	private Status status;
	
	/**
	 * The client notification status
	 */
	@Convert(converter = NotifyClientConverter.class)
	private NotifyClient notifyClient;
	
	/**
	 * The order quantum
	 */
	private double quantum;
	
	private Integer externalId;
	
	/**
	 * The status enum definition
	 * @authors acozma and bpopovici
	 *
	 */
	public enum Status {
	   PLACED("PLACED"),WAITING_ARRIVAL("WAITING_ARRIVAL"),ARRIVED("ARRIVED"),READY_FOR_PICKUP("READY_FOR_PICKUP"),DELIVERED("DELIVERED"),
	   CANCELED("CANCELED");
	   private String label;

	    private Status(String label) {
	        this.label = label;
	    }

	    public String getLabel() {
	        return label;
	    }
	}
	
	/**
	 * The notify client enum definition
	 * @author acozma and bpopovici
	 *
	 */
	public enum NotifyClient {
		   YES("YES"),NO("NO");
		   private String label;

		    private NotifyClient(String label) {
		        this.label = label;
		    }

		    public String getLabel() {
		        return label;
		    }
		}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public NotifyClient getNotifyClient() {
		return notifyClient;
	}

	public void setNotifyClient(NotifyClient notifyClient) {
		this.notifyClient = notifyClient;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getoDate() {
		return oDate;
	}

	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	public List<ProductQuantity> getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(List<ProductQuantity> productQuantity) {
		this.productQuantity = productQuantity;
	}

	public double getQuantum() {
		return quantum;
	}

	public void setQuantum(double quantum) {
		this.quantum = quantum;
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getExternalId() {
		return externalId;
	}

	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
	}
}