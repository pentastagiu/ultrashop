package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.Product;

/**
 * The product data access layer
 * @authors acozma and bpopovici
 *
 */
public interface ProductDAO extends JpaRepository<Product, Integer> {
}