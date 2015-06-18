package com.pentalog.sr.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sr.model.Feature;

/**
 * The feature data access layer
 * @authors acozma and bpopovici
 *
 */
public interface FeatureDAO  extends JpaRepository<Feature, Integer> {

	/**
	 * Method that returns a list of features for the given product id.
	 * @param id -	the product id
	 * @return
	 */
	List<Feature> findByProduct_Id(int id);
}