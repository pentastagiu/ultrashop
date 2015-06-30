package com.pentalog.sc.model;

import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.pentalog.sc.converter.StatusConverter;
import com.pentalog.sc.util.CustomDateSerializer;

/**
 * Model class for Order.
 * 
 * @author Abacioaiei
 *
 */
@XmlRootElement
@Entity
@Table(name = "orders")
public class Order {

    /**
     * Order id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    /**
     * Product from order.
     */
    @NotNull
    private Product product;

    /**
     * The quantity of the product.
     */
    @NotNull
    private int quantity;

    /**
     * The date the order is expected to be delivered.
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    // @JsonSerialize(using = CustomDateSerializer.class)
    private Date expectedDate;

    @Temporal(TemporalType.DATE)
    // @JsonSerialize(using = CustomDateSerializer.class)
    private Date deliveredDate;

    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status;

    public Order() {
    }

    /**
     * Order status enum type
     *
     */
    public enum Status {

        PLACED("PLACED"), WAITING_ARRIVAL("WAITING_ARRIVAL"), ARRIVED("ARRIVED"), DELIVERED(
                "DELIVERED"), CANCELED("CANCELED");

        private String label;

        private Status(String label) {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
