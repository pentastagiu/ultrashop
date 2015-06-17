package com.pentalog.us.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.us.model.ProductCategory;

/**
 * The product category data access layer
 * @authors acozma and bpopovici
 *
 */
public interface ProductCategoryDAO extends JpaRepository<ProductCategory, Integer> {
	
	List<ProductCategory> findByCategory_Id(int id);
}