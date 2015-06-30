package com.pentalog.sc.service;

import java.util.List;

import com.pentalog.sc.model.Order;

public interface OrderService {

    /**
     * Return a list with all orders from database.
     * 
     * @return
     */
    public List<Order> getOrders();

    /**
     * finds an order by id
     * 
     * @return - the order
     */
    public Order findById(int id);

    /**
     * Insert an order in database.
     * 
     * @param order
     * @return
     */
    public Order create(Order order);

    /**
     * Updates an order in database.
     * 
     * @param order
     * @return
     */
    public Order update(Order order);

    /**
     * Delete an order from database.
     * 
     * @param order
     * @return
     */
    public Order delete(Order order);
}
