package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.ProductGuarantee;

/**
 * The product guarantee data access layer
 * @authors acozma and bpopovici
 *
 */
public interface ProductGuaranteeDAO extends JpaRepository<ProductGuarantee, Integer> {
}