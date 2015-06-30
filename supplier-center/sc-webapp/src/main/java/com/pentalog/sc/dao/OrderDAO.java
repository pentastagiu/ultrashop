package com.pentalog.sc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pentalog.sc.model.Order;


public interface OrderDAO extends JpaRepository<Order, Integer>{

}
