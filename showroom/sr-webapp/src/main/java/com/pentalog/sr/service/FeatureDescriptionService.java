package com.pentalog.sr.service;

import java.util.List;
import com.pentalog.sr.model.FeatureDescription;
import com.pentalog.sr.model.Wrapper;

/**
* The feature description service
* @authors acozma and bpopovici
*
*/
public interface FeatureDescriptionService {
 
	/**
	 * Method that returns all feature descriptions of a product with given product id into a wrapper
	 * @param productId -	the product id
	 * @return
	 */
	Wrapper<FeatureDescription> getFeatureDescriptionsInWrapper(int productId);

	/**
	 * Method that returns all feature descriptions of a product with given product id into a list 
	 * @param productId -	the product id
	 * @return
	 */
	List<FeatureDescription> getFeatureDescriptions(int productId);

	/**
	 * Method that persists a feature description into the database
	 * @param featureDescription -	the feature description
	 * @return
	 */
	FeatureDescription saveFeatureDescription(FeatureDescription featureDescription);
	
	/**
	 * Method that returns a feature with the given id
	 * @param id -	the feature id
	 * @return
	 */
	FeatureDescription getFeatureById(int id);
}