package com.pentalog.us.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.us.model.Category;

/**
 * The category data access layer
 * @authors acozma and bpopovici
 *
 */
public interface CategoryDAO extends JpaRepository<Category, Integer> {
	
	List<Category> findByParent(int parent);
}