package com.pentalog.us.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.us.model.Supplier;

public interface SupplierDAO extends JpaRepository<Supplier, Integer>{
	/**
	 * Finds a supplier by id
	 */
	Supplier findById(int id);
	
	/**
	 * Finds a supplier by external id
	 */
	Supplier findByExternalId(int id);
	
	/**
	 * Updates an supplier
	 */
	
	   /**
     * Return a list of suppliers filtered by active.
     * @param active
     * @return
     */
    public List<Supplier> findByActive(Boolean active);
    
    /**
     * Return the number of entities from database filtered by active.
     * @param active
     * @return
     */
    public long countByActive(Boolean active);
}
