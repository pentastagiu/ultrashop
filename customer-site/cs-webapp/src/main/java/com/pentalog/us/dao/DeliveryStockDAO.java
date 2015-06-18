package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.DeliveryStock;

/**
 * The delivery stock data access layer
 * @authors acozma and bpopovici
 *
 */
public interface DeliveryStockDAO extends JpaRepository<DeliveryStock, Integer> {
}