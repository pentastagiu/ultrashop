package com.pentalog.us.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.Order;
import com.pentalog.us.model.Order.DeliveryMethod;

/**
 * The order data access layer
 * @authors acozma and bpopovici
 *
 */
public interface OrderDAO extends JpaRepository<Order, Integer> {
	
	List<Order> findOrderByDeliveryMethod(DeliveryMethod deliveryMethod);
}