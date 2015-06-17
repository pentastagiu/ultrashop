package com.pentalog.sr.dao;

import java.util.List;
import com.pentalog.sr.model.Order;
import com.pentalog.sr.model.Order.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The order data access layer
 * @authors acozma and bpopovici
 *
 */
public interface OrderDAO extends JpaRepository<Order, Integer> {
	
	/**
	 * Method that returns a list of orders sorted by the order date.
	 * @return
	 */
	List<Order> findAllByOrderByODateAsc();
	/**
	 * Method that returns a list of orders with the given status
	 * @param status - the order status
	 * @return
	 */
	List<Order> findByStatus(Status status);
	/**
	 * Method that returns a list of orders between an inferior limit and a superior limit
	 * @param offset - the inferior limit
	 * @param limit  - the superior limit
	 * @return
	 */ 
	 @Query(value = "select * from ( select a.*, ROWNUM offset from ( select * from orders order by oDate desc,id  ) a where rownum <= ?#{[1]}) where offset >= ?#{[0]}", nativeQuery = true)
	 List<Order> findAndLimit( Integer offset, Integer limit);
	 /**
	  * Method that counts the total number of orders
	  * @return
	  */
	 @Query(value="select count(id) from orders", nativeQuery = true)
	 Integer getNumberOfOrders();
	 
	 Order findByExternalId(int external_id);
}