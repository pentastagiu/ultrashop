package com.pentalog.us.ws;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductDescription;
import com.pentalog.us.model.ProductImagery;
import com.pentalog.us.service.GenerateProductsService;
import com.pentalog.us.service.ProductDescriptionService;
import com.pentalog.us.service.ProductImageryService;
import com.pentalog.us.service.ProductService;
import com.pentalog.us.util.ProductGeneration;

@Component
@Path("/generateProducts")
public class GenerateProductsWS {

    @Autowired
    GenerateProductsService generateProducts;

    @Autowired
    ProductService productService;

    @Autowired
    ProductDescriptionService productDescriptionService;

    @Autowired
    ProductImageryService productImageryService;

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<ProductGeneration> getProducts() {

        List<ProductGeneration> productsG = new ArrayList<ProductGeneration>();

        try {

            Document doc = Jsoup.connect(
                    "http://www.altex.ro/telefoane/smartphone").get();
            productsG = generateProducts.getListWithProducts(doc);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (ProductGeneration productG : productsG) {
            Product product = new Product();
            product.setName(productG.getTitle());
            product.setPrice(productG.getPrice());
            productService.putProduct(product);

            ProductDescription productD = new ProductDescription();
            productD.setDescription(productG.getDescription());
            productD.setProduct(product);
            productDescriptionService.putProductDescription(productD);

            ProductImagery productI = new ProductImagery();
            productI.setNumberOfImages(productG.getImages().size());
            productI.setProduct(product);
            productImageryService.putProductImagery(productI);

            // Save images
            int contor = 1;
            int folderNumber = product.getId();
            String destinationFolder = "D:\\sr-content\\images\\"
                    + folderNumber;
            boolean success = (new File(destinationFolder)).mkdirs();
            if (!success) {
                // Directory creation failed
            }

            for (String link : productG.getImages()) {
                String destinationFile = destinationFolder + "\\" + contor
                        + ".jpg";
                contor++;
                try {
                    generateProducts.saveImage(link, destinationFile);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return productsG;

    }

}
