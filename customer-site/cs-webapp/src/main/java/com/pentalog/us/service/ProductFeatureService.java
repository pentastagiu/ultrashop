package com.pentalog.us.service;

import java.util.List;
import java.util.Map;

import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductFeature;

/**
 * The product feature service
 * @authors acozma and bpopovici
 *
 */
public interface ProductFeatureService {	
	
	/**
	 * Method that get product feature by id
	 * @param id
	 * @return
	 */
	ProductFeature getProductFeatureById(int id);
	
	/**
	 * Method that get product features
	 * @return
	 */
	List<ProductFeature> getProductFeatures();
	
	/**
	 * Method that put product feature
	 * @param productFeature
	 */
	void putProductFeature(ProductFeature productFeature);
	
	/**
	 * Method that post product feature
	 * @param productFeature
	 */
	void postProductFeature(ProductFeature productFeature);
	
	/**
	 * Method that delete product feature
	 * @param productFeature
	 */
	void deleteProductFeature(ProductFeature productFeature);
	
	public Map<String, List<ProductFeature>> getProductFeatureByProductId(Product product);
}