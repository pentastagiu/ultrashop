package com.pentalog.sr.service;

import java.util.List;
import com.pentalog.sr.model.Feature;
import com.pentalog.sr.model.Wrapper;

/**
 * The feature service
 * @authors acozma and bpopovici
 *
 */
public interface FeatureService {

	/**
	 * Method that returns all features with the given product id into a wrapper
	 * @param productId -	the product id
	 * @return
	 */
	Wrapper<Feature> getAllFeatures_Wrapper(int productId);

	/**
	 * Method that returns all features with the given product id into a list
	 * @param productId -	the product id
	 * @return
	 */
	List<Feature> getAllFeatures_List(int id);

	/**
	 * Method that persists a product feature into the database
	 * @param productFeature -	the product feature
	 * @return
	 */
	Feature saveProductFeature(Feature productFeature);
	
	/**
	 * Method that returns a feature with the given id
	 * @param id -	the feature id
	 * @return
	 */
	Feature getById(int id);
}