package com.pentalog.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.sc.dao.ProductDAO;
import com.pentalog.sc.model.Product;

/**
 * Implement the methods from interface.
 * Provides actions regarding products.
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired 
    private ProductDAO productDao;
    
    @Override
    public List<Product> getProducts() {
        return productDao.findAll();
    }
    
}
