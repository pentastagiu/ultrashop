package com.pentalog.sc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.sc.model.Supplier;

/**
 * The product data access layer
 *
 */
public interface SupplierDAO extends JpaRepository<Supplier, Integer> {

}
