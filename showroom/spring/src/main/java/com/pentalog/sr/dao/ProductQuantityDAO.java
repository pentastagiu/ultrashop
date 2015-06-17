package com.pentalog.sr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.sr.model.ProductQuantity;

public interface ProductQuantityDAO extends JpaRepository<ProductQuantity, Integer>{
}
