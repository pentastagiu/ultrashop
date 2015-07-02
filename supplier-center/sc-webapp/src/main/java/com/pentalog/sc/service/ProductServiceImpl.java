package com.pentalog.sc.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pentalog.sc.dao.ProductDAO;
import com.pentalog.sc.model.Product;
import com.pentalog.sc.model.Stock;
import com.pentalog.sc.model.Supplier;

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

    @Override
    public void generateProducts(Supplier supplier) {
        String[] products;
        String fileData = this.getFile("products.txt");

        products = fileData.split("\n");

        for (String name : products) {
            Product product = new Product();
            product.setName(name);
            product.setPrice((double)Math.round((Math.random() * 100 + 1)*100)/100);
            product.setSupplier(supplier);
            
            int index = 1;
            while(productDao.findByName(product.getName()).size() != 0){
                product.setName(name + index);
                index++;
            }
            productDao.save(product);
        }

    }

    private String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        // Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }

}
