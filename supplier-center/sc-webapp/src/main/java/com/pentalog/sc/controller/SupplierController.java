package com.pentalog.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.Supplier;
import com.pentalog.sc.service.SupplierService;

/**
 * Controller for web services regarding products page.
 *
 */
@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    /**
     * Method that returns a string in json format, that contains all the
     * products from database.
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Supplier> getAllSuppliers() {
        return supplierService.getSuppliers();

    }

    /**
     * Method that returns a string in json format, that contains the product
     * with id in url.
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Supplier readProduct(@PathVariable int id) {
        return supplierService.findById(id);
    }

    /**
     * Method that inserts in database a product.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Supplier create(@RequestBody Supplier supplier) {
        return supplierService.create(supplier);
    }

    /**
     * Method that updates a product.
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Supplier update(@RequestBody Supplier supplier) {
        return supplierService.update(supplier);
    }

    /**
     * Delete a product from database.
     * 
     * @param stock
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Supplier deleteSupplier(@RequestBody Supplier supplier) {
        return supplierService.delete(supplier);
    }
}
