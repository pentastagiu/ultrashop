package com.pentalog.us.service;

import java.util.List;
import com.pentalog.us.model.Feature;

/**
 * The feature service
 * @authors acozma and bpopovici
 *
 */
public interface FeatureService {	
	
	/**
	 * Method that get feature by id
	 * @param id
	 * @return
	 */
	Feature getFeatureById(int id);
	
	/**
	 * Method that get features
	 * @return
	 */
	List<Feature> getFeatures();
	
	/**
	 * Method that put feature
	 * @param feature
	 */
	void putFeature(Feature feature);
	
	/**
	 * Method that post feature
	 * @param feature
	 */
	void postFeature(Feature feature);
	
	/**
	 * Method that delete feature
	 * @param feature
	 */
	void deleteFeature(Feature feature);
}