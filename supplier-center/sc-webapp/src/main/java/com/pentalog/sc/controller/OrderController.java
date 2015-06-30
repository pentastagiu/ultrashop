package com.pentalog.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.Order;
import com.pentalog.sc.service.OrderService;

/**
 * Controller for orders.
 * @author Abacioaiei
 *
 */
@Controller
@RequestMapping("/resources/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    /**
     * Method that returns a string in json format, that contains all the
     * orders from database.
     * 
     * @return
     */
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Order> getAllOrders() {

        List<Order> orders = orderService.getOrders();
        return orders;
    }

    /**
     * Method that returns a string in json format, that contains the order
     * with id in url.
     * 
     * @param id
     * @return
     */
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Order readOrder(@PathVariable int id) {
        return orderService.findById(id);
    }

    /**
     * Method that inserts in database an order.
     */
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Order create(@RequestBody Order order) {
        return orderService.create(order);
    }

    /**
     * Method that updates an order.
     */
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Order update(@RequestBody Order order) {
        return orderService.update(order);
    }

    /**
     * Delete a product from database.
     * 
     * @param stock
     * @return
     */
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Order deleteProduct(@RequestBody Order order) {
        return orderService.delete(order);
    }
}
