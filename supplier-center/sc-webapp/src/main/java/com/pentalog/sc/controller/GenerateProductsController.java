package com.pentalog.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pentalog.sc.model.Supplier;
import com.pentalog.sc.service.ProductService;
import com.pentalog.sc.service.SupplierService;

@Controller
@RequestMapping("/resources/generateProducts")
public class GenerateProductsController {

    @Autowired
    ProductService productService;
    
    @Autowired
    SupplierService supplierService;
    
    @Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String generateProducts() {

        List<Supplier> suppliers = supplierService.findByActive(Boolean.TRUE);
        
        productService.generateProducts(suppliers.get(0));
        return "true";
    }
}
