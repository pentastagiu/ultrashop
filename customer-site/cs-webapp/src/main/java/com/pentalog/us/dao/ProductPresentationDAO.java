package com.pentalog.us.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.us.model.ProductPresentation;

/**
 * The product presentation data access layer
 * @authors acozma and bpopovici
 *
 */
public interface ProductPresentationDAO extends JpaRepository<ProductPresentation, Integer> {
	
	List<ProductPresentation> findProductPresentationByProduct_IdOrderBySectionAsc(int product_id);
}