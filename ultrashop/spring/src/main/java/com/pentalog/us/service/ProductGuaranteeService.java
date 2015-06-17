package com.pentalog.us.service;

import java.util.List;
import com.pentalog.us.model.ProductGuarantee;

/**
 * The product guarantee service
 * @authors acozma and bpopovici
 *
 */
public interface ProductGuaranteeService {

	/**
	 * Method that get product guarantees
	 * @return
	 */
	List<ProductGuarantee> getProductGuarantees();
	
	/**
	 * Method that get product guarantee by id
	 * @param id
	 * @return
	 */
	ProductGuarantee getProductGuaranteeById(int id);
	
	/**
	 * Method that post product guarantee
	 * @param ProductGuarantee
	 */
	void postProductGuarantee(ProductGuarantee ProductGuarantee);
	
	/**
	 * Method that put product guarantee
	 * @param ProductGuarantee
	 */
	void putProductGuarantee(ProductGuarantee ProductGuarantee);
	
	/**
	 * Method that delete product guarantee
	 * @param ProductGuarantee
	 */
	void deleteProductGuarantee(ProductGuarantee ProductGuarantee);
}
