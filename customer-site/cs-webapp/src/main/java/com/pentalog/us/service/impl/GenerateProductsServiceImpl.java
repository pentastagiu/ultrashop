package com.pentalog.us.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.pentalog.us.service.GenerateProductsService;
import com.pentalog.us.util.ProductGeneration;

@Service("generateProducts")
public class GenerateProductsServiceImpl implements GenerateProductsService {

    @Override
    public List<String> getImages(Document doc) {

        List<String> images = new ArrayList<String>();
        Elements elements = doc.select(".product-images-thumbs a[href]");

        for (Element e : elements) {
            images.add(e.attr("href"));
        }
        return images;
    }

    @Override
    public Double getPrice(Document doc) {

        String price;
        String[] parts;
        String[] parts2;
        Elements elements = doc.select(".price");

        Element e = elements.get(0);
        price = e.text();
        parts = price.split(" ");
        parts[0] = parts[0].replaceAll("\\.", "");
        parts[0] = parts[0].replaceAll(",", ".");
        return Double.parseDouble(parts[0]);
    }

    @Override
    public String getTitle(Document doc) {
        String title;
        Elements elements = doc.select(".product-name.grid12-10.alpha h1");

        Element e = elements.get(0);
        title = e.text();
        return title;
    }

    @Override
    public String getDescription(Document doc) {

        String description;
        Elements elements = doc.select(".std");

        Element e = elements.get(0);
        description = e.text();

        return description;
    }

    @Override
    public List<String> getProductLinks(Document doc) {

        List<String> links = new ArrayList<String>();
        Elements elements = doc.select(".product-image");

        for (Element element : elements) {
            links.add(element.attr("href"));
        }
        return links;
    }

    @Override
    public List<ProductGeneration> getListWithProducts(Document doc)
            throws IOException {

        List<ProductGeneration> products = new ArrayList<ProductGeneration>();

        List<String> productLinks = new ArrayList<String>();
        productLinks = this.getProductLinks(doc);

        for (String link : productLinks) {
            ProductGeneration productGeneration = new ProductGeneration();
            Document productPage = Jsoup.connect(link).get();
            productGeneration.setTitle(this.getTitle(productPage));
            productGeneration.setPrice(this.getPrice(productPage));
            productGeneration.setDescription(this.getDescription(productPage));
            productGeneration.setImages(this.getImages(productPage));
            products.add(productGeneration);

        }

        return products;
    }

    public void saveImage(String imageUrl, String destinationFile)
            throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}
