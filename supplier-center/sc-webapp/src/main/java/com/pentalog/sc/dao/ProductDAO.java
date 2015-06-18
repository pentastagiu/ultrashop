package com.pentalog.sc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sc.model.Product;

/**
 * The product data access layer
 *
 */
public interface ProductDAO extends JpaRepository<Product, Integer> {
}