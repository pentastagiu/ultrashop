package com.pentalog.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pentalog.sc.dao.OrderDAO;
import com.pentalog.sc.model.Order;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDao;

    @Override
    public List<Order> getOrders() {

        return orderDao.findAll();
    }

    @Override
    public Order findById(int id) {

        return orderDao.findOne(id);
    }

    @Override
    public Order create(Order order) {

        return orderDao.save(order);
    }

    @Override
    @Transactional
    public Order update(Order order) {
        Order orderToUpdate = orderDao.findOne(order.getId());
        if (orderToUpdate != null) {
            orderToUpdate.setId(order.getId());
            orderToUpdate.setProduct(order.getProduct());
            orderToUpdate.setQuantity(order.getQuantity());
            orderToUpdate.setDeliveredDate(order.getDeliveredDate());
            orderToUpdate.setExpectedDate(order.getExpectedDate());
            orderToUpdate.setStatus(order.getStatus());
        }
        return orderToUpdate;
    }

    @Override
    @Transactional
    public Order delete(Order order) {
        Order orderToDelete = orderDao.findOne(order.getId());
        orderToDelete.setId(order.getId());
        orderToDelete.setProduct(order.getProduct());
        orderToDelete.setQuantity(order.getQuantity());
        orderToDelete.setDeliveredDate(order.getDeliveredDate());
        orderToDelete.setExpectedDate(order.getExpectedDate());
        orderToDelete.setStatus(order.getStatus());
        orderDao.delete(orderToDelete);

        return orderToDelete;

    }

    @Override
    public List<Order> readOrdersByPage(int pageIndex, int offset) {
        PageRequest request = new PageRequest(pageIndex, offset);
        Page<Order> page = orderDao.findAll(request);
        return page.getContent();
    }

}
