package com.pentalog.sc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The stock java database model
 *
 */
@XmlRootElement
@Entity
@Table(name="stock")
public class Stock {

    
    /**
     * Product id
     */
    @Id
    @NotNull
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    /**
     * Product in stock.
     */
    @OneToOne
    private Product product;
    
    /**
     * Count of product in stock.
     */
    private int amount;
    
    /**
     * Supplier for the stock
     */
    @ManyToOne
    @JoinColumn (name = "supplier_id")
    private Supplier supplier;
    
    public Stock() {
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


    public int getAmount() {
        return amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
  
    
}
