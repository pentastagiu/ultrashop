package com.pentalog.sr.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pentalog.sr.model.Product;

/**
 * The product data access layer
 * @authors acozma and bpopovici
 *
 */
public interface ProductDAO extends JpaRepository<Product, Integer> {
	
	/**
	 * Method that returns a product by name
	 * @param name -	the product name
	 * @return
	 */
	Product findByName(String name);
	/**
	 * Method that returns a list of products between an inferior limit and a superior limit
	 * @param offset - the inferior limit
	 * @param limit  - the superior limit
	 * @return
	 */ 
	@Query(value = "select * from ( select a.*, ROWNUM offset from ( select * from product order by name ) a where rownum <= ?#{[1]}) where offset >= ?#{[0]}", nativeQuery = true)
	 List<Product> findAndLimit( Integer offset, Integer limit);
	/**
	  * Method that counts the total number of products
	  * @return 
	  */
	 @Query(value="select count(id) from product", nativeQuery = true)
	 Integer getNumberOfProducts();
}