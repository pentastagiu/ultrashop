package com.pentalog.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pentalog.sc.dao.SupplierDAO;
import com.pentalog.sc.model.Product;
import com.pentalog.sc.model.Stock;
import com.pentalog.sc.model.Supplier;

/**
 * Implement the methods from interface. Provides actions regarding products.
 *
 */
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDAO supplierDao;

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

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
            supplierToUpdate.setActive(supplier.isActive());
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
        Supplier supplierToDelete = supplierDao.findOne(supplier.getId());
        supplierToDelete.setActive(Boolean.FALSE);
        List<Stock> stocks = stockService.findBySupplierId(supplier.getId());
        List<Product> products = productService.findBySupplierId(supplier
                .getId());
        if (stocks != null) {
            for (Stock stock : stocks) {
                stockService.delete(stock);
            }
        }
        if (products != null) {
            for (Product product : products) {
                productService.delete(product);
            }
        }

        return supplierToDelete;
    }

    public List<Supplier> findByActive(Boolean active) {
        return supplierDao.findByActive(active);
    }

    @Override
    public long countByActive(Boolean active) {
        return supplierDao.countByActive(active);
    }

    @Override
    public List<Supplier> readSuppliersByPage(int pageIndex, int offset) {

        PageRequest request = new PageRequest(pageIndex, offset);
        Page<Supplier> page = supplierDao.findAll(request);
        return page.getContent();

    }
}
