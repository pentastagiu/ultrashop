package com.pentalog.sc.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.pentalog.sc.converter.BooleanTFConverter;

/**
 * The supplier java database model
 *
 */
@XmlRootElement
@Entity
@Table(name="supplier")
public class Supplier {

    /**
     * Supplier id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    private Integer id;
    
    /**
     * Name of supplier.
     */
    private String name;
    
    /**
     * Email of the supplier.
     */
    private String email;
    
    
    /**
     * Contact details for supplier
     */
    private String contactDetails;
    
    /**
     * Status of the supplier.
     */
    @Convert(converter=BooleanTFConverter.class)
    private Boolean active;
    
    public Supplier() {
	}
    
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

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
}
