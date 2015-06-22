package com.pentalog.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.Product;
import com.pentalog.sc.service.ProductService;

/**
 * Controller for web services regarding products page.
 *
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * Method that returns a string in json format, that contains all the
     * products from database.
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Product> getAllProducts() {
         
        List<Product> p=productService.getProducts();
        return p;
    }

    /**
     * Method that returns a string in json format, that contains the product
     * with id in url.
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Product readProduct(@PathVariable int id) {
        return productService.findById(id);
    }

    /**
     * Method that inserts in database a product.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    /**
     * Method that updates a product.
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    /**
     * Delete a product from database.
     * 
     * @param stock
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Product deleteProduct(@RequestBody Product product) {
        return productService.delete(product);
    }
}
