package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.ProductImagery;

/**
 * The product imagery data access layer
 * @authors acozma and bpopovici
 *
 */
public interface ProductImageryDAO extends JpaRepository<ProductImagery, Integer> {
	
	ProductImagery findProductImageryByProduct_Id(int product_id);
}