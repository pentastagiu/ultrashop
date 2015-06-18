package com.pentalog.sc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The supplier java database model
 *
 */
@XmlRootElement
@Entity
@Table(name="supplier")
public class Supplier {

    /**
     * Product id
     */
    @Id
    @GeneratedValue
    private Integer id;
    
    /**
     * Name of supplier.
     */
    private String name;
    
    /**
     * List of stocks the supplier has.
     */
    @OneToMany
    private List<Stock> stocks;
    
    /**
     * Email of the supplier.
     */
    private String email;

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

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
