package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.ProductDescription;

/**
 * The product description data access layer
 * @authors acozma and bpopovici
 *
 */
public interface ProductDescriptionDAO extends JpaRepository<ProductDescription, Integer> {
	
	ProductDescription findProductDescriptionByProduct_Id(int product_id);
}