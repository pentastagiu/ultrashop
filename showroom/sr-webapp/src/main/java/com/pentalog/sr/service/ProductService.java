package com.pentalog.sr.service;

import java.util.List;

import com.pentalog.sr.model.Feature;
import com.pentalog.sr.model.FeatureDescription;
import com.pentalog.sr.model.Product;
import com.pentalog.sr.model.ProductImagery;
import com.pentalog.sr.model.Wrapper;

/**
 * The product service
 * @authors acozma and bpopovici
 *
 */
public interface ProductService {

    /**
     * Method that returns all the products into a wrapper
     * @return
     */
    Wrapper<Product> getAllProducts_Wrapper();
    
    /**
     * Method that returns all the products into a list
     * @return
     */
    List<Product> getAllProducts_List();

    /**
     * Method that returns a product with the given id
     * @param id - the product id
     * @return
     */
    Product getProductById(int id);
    
    /**
     * Method that returns a product with the given product name
     * @param name - the product name
     * @return
     */
    Product getProductByName(String name);
    
    /**
     * Method that persists a product in the database
     * @param product - the product
     * @return
     */
    Product registerProduct(Product product);
    
    /**
     * Method that returns a list of filtered products by a product name
     * @param filterName - the product name
     * @param products - the list of all products
     * @return
     */
    List<Product> filterByName(String filterName, List<Product> products);
    
    /**
     * Method that returns a list of filtered products by a range of price
     * @param filterMinPrice - the minimum price range
     * @param filterMaxPrice - the maximum price range
     * @param products - the list of all products
     * @return
     */
    List<Product> filterByPrice(String filterMinPrice, String filterMaxPrice, List<Product> products);
    
    /**
     * Method that returns a list of products that contain in their description or feature descriptions a given search value
     * @param value -   the searched value
     * @return
     */
    List<Product> searchProduct(String value);
    
    /**
     * Method that updates the solr document
     */
    void updateSolrDocs();

    /**
     * 
     * Method that updates information for a given product
     * @param product
     * @return
     */
    Product modifyProduct(Product product);
    
    /**
     * Method that returns a list of products between an inferior limit and a superior limit
     * @param offset - the inferior limit
     * @param limit  - the superior limit
     * @return
     */ 
    List<Product> getTopProducts(Integer offset, Integer limit);
    
    /**
     * Method that counts the total number of products
     * @return
     */
    Integer getNumberOfProducts();
    
    void syncProducts(List<Product> products);
    
    void syncProductsImagery(List<ProductImagery> productsImageries);
    
    void syncProductsFeature(List<Feature> features);
    
    void syncProductsFeatureDescription(List<FeatureDescription> featureDescriptions);
}