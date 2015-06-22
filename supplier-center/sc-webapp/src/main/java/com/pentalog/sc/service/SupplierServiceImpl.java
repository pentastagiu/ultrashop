package com.pentalog.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pentalog.sc.dao.SupplierDAO;
import com.pentalog.sc.model.Supplier;

/**
 * Implement the methods from interface. Provides actions regarding products.
 *
 */
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDAO supplierDao;

    public List<Supplier> getSuppliers() {
        return supplierDao.findAll();
    }

    /**
     * @see {link ProductService.findById}
     */
    @Override
    public Supplier findById(int id) {
        return supplierDao.findOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supplier create(Supplier supplier) {
        return supplierDao.save(supplier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Supplier update(Supplier supplier) {
        Supplier supplierToUpdate = supplierDao.findOne(supplier.getId());
        if (supplierToUpdate != null) {
            supplierToUpdate.setName(supplier.getName());
            supplierToUpdate.setEmail(supplier.getEmail());
            supplierToUpdate.setContactDetails(supplier.getContactDetails());
        }
        return supplierToUpdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Supplier delete(Supplier supplier) {
        Supplier supplierToDelete = new Supplier();
        supplierToDelete.setId(supplier.getId());
        supplierToDelete.setName(supplier.getName());
        supplierToDelete.setEmail(supplier.getEmail());
        supplierToDelete.setContactDetails(supplier.getContactDetails());
        supplierDao.delete(supplierToDelete);

        return supplierToDelete;
    }
}
