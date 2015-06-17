package com.pentalog.sr.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sr.model.DeliveryStock;

/**
 * The delivery stock data access layer
 * @authors acozma and bpopovici
 *
 */
public interface DeliveryStockDAO extends JpaRepository<DeliveryStock,Integer>{
	
	/**
	 * Method that returns a delivery stock for the given product id.
	 * @param product_Id -	the product id
	 * @return
	 */
	DeliveryStock findByProduct_Id(int product_Id);
	
	/**
	 * Method that returns a delivery stock by the given order id and product id.
	 * @param order_Id -	the order id
	 * @param product_Id -	the product id
	 * @return
	 */
	List<DeliveryStock> findByOrder_IdAndProduct_Id(int order_Id, int product_Id);
	
	/**
	 * Method that returns a list of delivery stocks for the given order id.
	 * @param order_Id -	the order id
	 * @return
	 */
	List<DeliveryStock> findByOrder_Id(int order_Id);
}