package com.pentalog.sc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.sc.model.Order;
import com.pentalog.sc.model.Order.Status;


public interface OrderDAO extends JpaRepository<Order, Integer>{

    /**
     * Method that returns a list of orders with the given status
     * @param status - the order status
     * @return
     */
    List<Order> findByStatus(Status status);
}
