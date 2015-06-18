package com.pentalog.us.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.pentalog.us.converter.DeliveryMethodConverter;
import com.pentalog.us.converter.NotifyClientConverter;
import com.pentalog.us.converter.PaymentMethodConverter;
import com.pentalog.us.converter.StatusConverter;
import com.pentalog.us.util.PropertyMapDeserializer;

/**
 * The order java database model
 * @authors acozma and bpopovici
 *
 */
@XmlRootElement
@Entity
@Table(name="orders")
public class Order {

	/**
	 * Order id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * Order customer
	 */
	@NotNull
	private Customer customer;
	
	/**
	 * Order card
	 */
	private Card card;
	
	/**
	 * Order oDate
	 */
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date oDate;
	
	/**
	 * Order products
	 */
	@NotNull
	@ElementCollection
	private Map<Product,Integer> products;
	
	/**
	 * Order status
	 */
	@NotNull
	@Convert(converter = StatusConverter.class)
	private Status status;
	
	/**
	 * Order notify client
	 */
	@NotNull
	@Convert(converter = NotifyClientConverter.class)
	private NotifyClient notifyClient;
	
	/**
	 * Order payment method
	 */
	@NotNull
	@Convert(converter = PaymentMethodConverter.class)
	private PaymentMethod paymentMethod;
	
	/**
	 * Delivery stock method
	 */
	@Convert(converter = DeliveryMethodConverter.class)
	private DeliveryMethod deliveryMethod;
	
	/**
	 * Order quantum
	 */
	@NotNull
	private double quantum;

	/**
	 * Order status enum type
	 * @author acozma and bpopovici
	 *
	 */
	public enum Status {
		
		PLACED("PLACED"), WAITING_ARRIVAL("WAITING_ARRIVAL"), ARRIVED("ARRIVED"), READY_FOR_PICKUP("READY_FOR_PICKUP"), DELIVERED("DELIVERED"), CANCELED("CANCELED");
		
		private String label;

		private Status(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	/**
	 * Order notify client enum type
	 * @author acozma and bpopovici
	 *
	 */
	public enum NotifyClient {
		
		YES("YES"), NO("NO");
		
		private String label;

		private NotifyClient(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}
	
	/**
	 * Order payment method enum type
	 * @author acozma and bpopovici
	 *
	 */
	public enum PaymentMethod {
		
		CARD("CARD"), DOOR("DOOR");
		
		private String label;

		private PaymentMethod(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	/**
	 * Delivery method enum type
	 * @author acozma and bpopovici
	 *
	 */
	public enum DeliveryMethod {
		
		MAIL("MAIL"), CURRIER("CURRIER"),PICKUP("PICKUP");
		
		private String label;

		private DeliveryMethod(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Date getoDate() {
		return oDate;
	}

	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}

	@JsonDeserialize(using = PropertyMapDeserializer.class) 
	public Map<Product, Integer> getProducts() {
		return products;
	}
	
	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
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

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getQuantum() {
		return quantum;
	}

	public void setQuantum(double quantum) {
		this.quantum = quantum;
	}
	
	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
}