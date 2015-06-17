package com.pentalog.us.service;

import java.util.List;

import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductCategory;

/**
 * The product category service
 * @authors acozma and bpopovici
 *
 */
public interface ProductCategoryService {	
	
	/**
	 * Method that get product category by id
	 * @param id
	 * @return
	 */
	ProductCategory getProductCategoryById(int id);
	
	/**
	 * Method that get product categories
	 * @return
	 */
	List<ProductCategory> getProductCategories();
	
	/**
	 * Method that put product category
	 * @param productCategory
	 */
	void putProductCategory(ProductCategory productCategory);
	
	/**
	 * Method that post product category
	 * @param productCategory
	 */
	void postProductCategory(ProductCategory productCategory);
	
	/**
	 * Method that delete product category
	 * @param productCategory
	 */
	void deleteProductCategory(ProductCategory productCategory);
	
	List<Product> getProductsByCategoryId(int id);
}