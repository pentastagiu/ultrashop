package com.pentalog.sc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pentalog.sc.dao.ProductDAO;
import com.pentalog.sc.model.Product;
import com.pentalog.sc.model.Stock;

/**
 * Implement the methods from interface. Provides actions regarding products.
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDao;

    @Autowired
    private StockService stockService;

    @Override
    public List<Product> getProducts() {
        return productDao.findAll();
    }

    /**
     * @see {link ProductService.findById}
     */
    @Override
    public Product findById(int id) {
        return productDao.findOne(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product create(Product product) {
        return productDao.save(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Product update(Product product) {
        Product productToUpdate = productDao.findOne(product.getId());
        if (productToUpdate != null) {
            productToUpdate.setName(product.getName());
            productToUpdate.setSupplier(product.getSupplier());
            productToUpdate.setPrice(product.getPrice());
        }
        return productToUpdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Product delete(Product product) {
        Product productToDelete = new Product();
        productToDelete.setId(product.getId());
        productToDelete.setName(product.getName());
        productToDelete.setPrice(product.getPrice());
        productToDelete.setSupplier(product.getSupplier());

        List<Stock> stocks = stockService.findByProductId(product.getId());
        for (Stock stock : stocks) {
            stockService.delete(stock);
        }
        
        productDao.delete(productToDelete);
        
        return productToDelete;
    }

    @Override
    public List<Product> findBySupplierId(int id) {
        List<Product> products;
        products = productDao.findBySupplierId(id);
        return products;
    }

    @Override
    public long count() {
        return productDao.count();
    }

    @Override
    public List<Product> readProductsByPage(int pageIndex, int offset) {

        PageRequest request = new PageRequest(pageIndex, offset);
        Page<Product> page = productDao.findAll(request);
        return page.getContent();
    }
}
