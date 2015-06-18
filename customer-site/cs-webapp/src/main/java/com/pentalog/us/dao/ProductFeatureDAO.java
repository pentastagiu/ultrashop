package com.pentalog.us.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.ProductFeature;

/**
 * The product feature data access layer
 * @authors acozma and bpopovici
 *
 */
public interface ProductFeatureDAO extends JpaRepository<ProductFeature, Integer>{
	
	List<ProductFeature> findProductFeatureByProduct_IdOrderByPriorityAsc(int id);
}