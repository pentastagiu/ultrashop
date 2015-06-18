package com.pentalog.sr.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sr.model.FeatureDescription;

/**
 * The feature description data access layer
 * @authors acozma and bpopovici
 *
 */
public interface FeatureDescriptionDAO  extends JpaRepository<FeatureDescription, Integer> {

	 /**
	  * Method that returns the a list of feature descriptions for the given product id, sorted by the section number.
	  * @param product_Id -	the product id
	  * @return
	  */
	List<FeatureDescription> findByProduct_IdOrderBySectionNumberAsc(int product_Id);
}