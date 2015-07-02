package com.pentalog.sc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.sc.model.Product;

/**
 * The product data access layer
 *
 */
public interface ProductDAO extends JpaRepository<Product, Integer> {
    
    /**
     * 
     * @param id
     * @return
     */
    List<Product> findBySupplierId(int id);
    
    /**
     * The products with the same name.
     * @param name
     * @return
     */
    List<Product> findByName(String name);

}