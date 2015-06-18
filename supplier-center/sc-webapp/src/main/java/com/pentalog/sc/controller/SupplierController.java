package com.pentalog.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * Method that returns a string in json format, that contains all the products from database.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Supplier> getAllSuppliers() {
        return supplierService.getSuppliers();
        
    }
}
