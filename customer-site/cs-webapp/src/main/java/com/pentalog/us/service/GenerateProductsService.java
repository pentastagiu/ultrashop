package com.pentalog.us.service;
import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;

import com.pentalog.us.util.ProductGeneration;

public interface GenerateProductsService {

    /**
     * String with links to images.
     * @param doc
     * @return
     */
    public List<String> getImages(Document doc);
    
    /**
     * String with price of product.
     * @return
     */
    public Double getPrice(Document doc);
    
    /**
     * String with title of the product.
     * @return
     */
    public String getTitle(Document doc);
    
    /**
     * String with description of product.
     * @return
     */
    public String getDescription(Document doc);
    
    /**
     * Return a list of links.
     * @param doc
     * @return
     */
    public List<String> getProductLinks(Document doc);
    
    /**
     * Return a string containing all data of the products
     * @param doc
     * @return
     * @throws IOException 
     */
    public List<ProductGeneration> getListWithProducts(Document doc) throws IOException;
    
    /**
     * Save an image from an url.
     * @param imageUrl
     * @param destinationFile
     * @throws IOException
     */
    public void saveImage(String imageUrl, String destinationFile) throws IOException;
}
