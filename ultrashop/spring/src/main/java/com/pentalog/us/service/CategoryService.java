package com.pentalog.us.service;

import java.util.List;
import java.util.Map;

import com.pentalog.us.model.Category;

/**
 * The category service
 * @authors acozma and bpopovici
 *
 */
public interface CategoryService {	
	
	/**
	 * Method that get category by id
	 * @param id
	 * @return
	 */
	Category getCategoryById(int id);
	
	/**
	 * Method that get categories
	 * @return
	 */
	List<Category> getCategories();
	
	/**
	 * Method that put category
	 * @param category
	 */
	void putCategory(Category category);
	
	/**
	 * Method that post category
	 * @param category
	 */
	void postCategory(Category category);
	
	/**
	 * Method that delete category
	 * @param category
	 */
	void deleteCategory(Category category);
	
	List<Category> getCategoryByParent(int parent);
	
	Map<String,List<Category>> getCategoriesMap();
}