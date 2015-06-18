package com.pentalog.sc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    @GeneratedValue
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
    
}
