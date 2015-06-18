package com.pentalog.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.sc.dao.SupplierDAO;
import com.pentalog.sc.model.Supplier;

/**
 * Implement the methods from interface. Provides actions regarding products.
 *
 */
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierDAO supplierDao;

    public List<Supplier> getSuppliers() {
        return supplierDao.findAll();
    }
}
