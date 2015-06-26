package com.pentalog.sc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.sc.model.Supplier;

/**
 * The product data access layer
 *
 */
public interface SupplierDAO extends JpaRepository<Supplier, Integer> {

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
