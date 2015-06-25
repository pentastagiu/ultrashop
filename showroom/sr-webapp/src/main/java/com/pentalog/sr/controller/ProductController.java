package com.pentalog.sr.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pentalog.sr.model.AvailabilityStock;
import com.pentalog.sr.model.Feature;
import com.pentalog.sr.model.FeatureDescription;
import com.pentalog.sr.model.Product;
import com.pentalog.sr.model.ProductImagery;
import com.pentalog.sr.model.Wrapper;
import com.pentalog.sr.service.AvailabilityStockService;
import com.pentalog.sr.service.FeatureDescriptionService;
import com.pentalog.sr.service.FeatureService;
import com.pentalog.sr.service.ProductImageryService;
import com.pentalog.sr.service.ProductService;

/**
 * Controller for web  services regarding product operations
 * @authors acozma and bpopovici
 *
 */
@Controller
@RequestMapping(value = "/resources/products")
public class ProductController {

    /**
     *  The product service - contains methods used to fetch data regarding products from the DAO layer.
     */
    @Autowired
    private ProductService productService;
    
    /**
     *  The availability stock service - contains methods used to fetch data regarding product's stocks from the DAO layer.
     */
    @Autowired
    private AvailabilityStockService availabilityStockService;
    
    /**
     *  The feature service - contains methods used to fetch data regarding a feature of the product from the DAO layer.
     */
    @Autowired
    private FeatureService featureService;
    
    /**
     * The product imagery service - contains methods to fetch data regarding product imagery from the DAO layer.
     */
    @Autowired
    private ProductImageryService productImageryService;
    
    /**
     * The feature description service - contains methods to fetch data regarding feature description from the DAO layer
     */
    @Autowired
    private FeatureDescriptionService featureDescriptionService; 
    
    /**
     * Web service that retrieves all products from the database using the service provided by productService.
     * Secured with spring security, accessed only by users with admin or operator privilege.
     * @return
     */
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Wrapper<Product> getAllProducts() {
        return productService.getAllProducts_Wrapper();
    }

    /**
     * Web service that retrieves a product from the database by its id, using the service provided by productService.
     * Secured with spring security, accessed only by users with admin or operator privilege.
     * @param id -  product id
     * @return
     */
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }
    
    /**
     * Web service that persists a product in the database, using the service provided by productService.
     * Secured with spring security, accessed only by users with admin privilege.
     * @param product - The product that will be saved in the database
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Product registerProduct(@RequestBody Product product) {
        return productService.registerProduct(product);
    }
    
    /**
     * Web service that updates information about a product in the database, using the service provided by productService.
     * Secured with spring security, accessed only by users with admin privilege.
     * @param product - The product that will be updated in the database
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Product updateProduct(@RequestBody Product product) {
        return productService.modifyProduct(product);
    }
    
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(value = "/sync", method = RequestMethod.POST)
    public @ResponseBody void syncProducts(@RequestBody List<Product> products) {
        productService.syncProducts(products);
    }
    
    /**
     * Web service that retrieves a product availability stock from the database by product id, using the service provided by the availabilityStock
     * service.
     * Secured with spring security, accessed only by users with admin or operator privilege.
     * @param id -  availabilityStock id
     * @return
     */
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(value = "/stocks/{id}", method = RequestMethod.GET)
    public @ResponseBody AvailabilityStock getProductStockByProductId(@PathVariable int id) {
        return availabilityStockService.getStockById(id);
    }
    
    /**
     * Web service that retrieves product features from the database by the product id, using the service provided by featureService.
     * Secured with spring security, accessed only by users with admin or operator privilege.
     * @param id -  availabilityStock id
     * @return
     */
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(value = "/features/{id}", method = RequestMethod.GET)
    public @ResponseBody Wrapper<Feature> getProductFeatures(@PathVariable int id) {
        return featureService.getAllFeatures_Wrapper(id);
    }
    
    /**
     * Web service that persists a product feature in the database, using the service provided by featureService.
     * Secured with spring security, accessed only by users with admin privilege.
     * @param product - The feature that will be saved in the database
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/features",method = RequestMethod.PUT)
    public @ResponseBody Feature addProductFeature(@RequestBody Feature feature) {
        return featureService.saveProductFeature(feature);
    }
    
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(value = "/features/sync", method = RequestMethod.POST)
    public @ResponseBody void syncFeatures(@RequestBody List<Feature> features) {
        productService.syncProductsFeature(features);
    }
    
    /**
     * Web service that updates a product feature in the database, using the service provided by featureService.
     * Secured with spring security, accessed only by users with admin privilege.
     * @param product - The feature that will be updated in the database
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/features",method = RequestMethod.POST)
    public @ResponseBody Feature updateProductFeature(@RequestBody Feature feature) {
        return featureService.saveProductFeature(feature);
    }
 
    /**
     * Web service that retrieves all the product feature descriptions from the database by product id, using the service provided by the 
     * featureDescription service.
     * Secured with spring security, accessed only by users with admin or operator privilege.
     * @param id -  the product id
     * @return
     */
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(value = "/featureDescription/{id}", method = RequestMethod.GET)
    public @ResponseBody Wrapper<FeatureDescription> getFeatureDescriptions(@PathVariable int id) {
        return featureDescriptionService.getFeatureDescriptionsInWrapper(id);
    }
    
    /**
     * Web service that persists a product feature description in the database, using the service provided by the featureDescription service.
     * Secured with spring security, accessed only by users with admin privilege.
     * @param featureDescription -  The feature description that will be saved in the database
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/featureDescription", method = RequestMethod.PUT)
    public @ResponseBody FeatureDescription addProductFeatureDescription(@RequestBody FeatureDescription featureDescription) {
        return featureDescriptionService.saveFeatureDescription(featureDescription);
    }
    
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(value = "/featureDescription/sync", method = RequestMethod.POST)
    public @ResponseBody void syncFeatureDescription(@RequestBody List<FeatureDescription> featureDescriptions) {
        productService.syncProductsFeatureDescription(featureDescriptions);
    }
    
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(value = "/images/sync", method = RequestMethod.POST)
    public @ResponseBody void syncProductImagery(@RequestBody List<ProductImagery> productsImageries) {
        productService.syncProductsImagery(productsImageries);
    }
    
    /**
     * Web service that updates a product feature description in the database, using the service provided by the featureDescription service.
     * Secured with spring security, accessed only by users with admin privilege.
     * @param featureDescription -  The feature description that will be updated in the database
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/featureDescription", method = RequestMethod.POST)
    public @ResponseBody FeatureDescription updateProductFeatureDescription(@RequestBody FeatureDescription featureDescription) {
        return featureDescriptionService.saveFeatureDescription(featureDescription);
    }
    
    /**
     * Web service that retrieves a list of products between a superior and inferior limit, using the service provided by productService. Secured with spring security, accessed only by
     * users with admin or operator privilege.
     * @return
     */
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(value = "/{offset}/{limit}",method = RequestMethod.GET)
    public @ResponseBody List<Product> getTopProducts(@PathVariable Integer offset,@PathVariable Integer limit) {
        return productService.getTopProducts(offset,limit);
    }
    
    /**
     * Web service that counts the total number of products using the service provided by productService. Secured with spring security, accessed only by
     * users with admin or operator privilege.
     * @return
     */
    @Secured({"ROLE_ADMIN", "ROLE_OPERATOR"})
    @RequestMapping(value="/total",method = RequestMethod.GET)
    public @ResponseBody String getNumberOfProducts() {
        return productService.getNumberOfProducts().toString();
    }
}