package com.pentalog.us.ws;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pentalog.us.model.AvailabilityStock;
import com.pentalog.us.model.Product;
import com.pentalog.us.model.ProductCategory;
import com.pentalog.us.model.ProductDescription;
import com.pentalog.us.model.ProductFeature;
import com.pentalog.us.model.ProductImagery;
import com.pentalog.us.model.ProductPresentation;
import com.pentalog.us.service.AvailabilityStockService;
import com.pentalog.us.service.ProductCategoryService;
import com.pentalog.us.service.ProductDescriptionService;
import com.pentalog.us.service.ProductFeatureService;
import com.pentalog.us.service.ProductImageryService;
import com.pentalog.us.service.ProductPresentationService;
import com.pentalog.us.service.ProductService;

/**
 * Product web services
 * @authors acozma and bpopovici
 *
 */
@Component
@Path("/products")
public class ProductWS {

	/**
	 * Product service
	 */
	@Autowired
	ProductService productService;
	
	/**
	 * Product category service
	 */
	@Autowired
	ProductCategoryService productCategoryService;
	
	/**
	 * Product description service
	 */
	@Autowired
	ProductDescriptionService productDescriptionService;
	
	/**
	 * Product feature service
	 */
	@Autowired
	ProductFeatureService productFeatureService;
	
	/**
	 * Product imagery service
	 */
	@Autowired
	ProductImageryService productImageryService;
	
	/**
	 * Product presentation service
	 */
	@Autowired
	ProductPresentationService productPresentationService;
	
	/**
	 * Availability stock service
	 */
	@Autowired
	AvailabilityStockService availabilityStockService;

	/**
	 * Web service that get products
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Product> getProducts() {
		return productService.getProducts();
	}
	
	/**
	 * Web service that get product by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Product getProductById(@PathParam("id") int id)
	{
		return productService.getProductById(id);
	}
	
	/**
	 * Web service that post product
	 * @param order
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postProduct(Product product) {
		productService.postProduct(product);
	}
	
	/**
	 * Web service that put product
	 * @param order
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putProduct(Product product) {
		productService.putProduct(product);
	}
	
	/**
	 * Web service that delete product
	 * @param order
	 */
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteProduct(Product product) {
		productService.deleteProduct(product);
	}
	
	/**
	 * Web service that get products categories
	 * @return
	 */
	@GET
	@Path("/categories")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ProductCategory> getProductCategories() {
		return productCategoryService.getProductCategories();
	}
	
	/**
	 * Web service that get product category by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/categories/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProductCategory getProductCategoryById(@PathParam("id") int id)
	{
		return productCategoryService.getProductCategoryById(id);
	}
	
	@GET
	@Path("/categories/category/{category_id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Product> getProductsByCategoryId(@PathParam("category_id") int id)
	{
		return productCategoryService.getProductsByCategoryId(id);
	}
	
	/**
	 * Web service that post product category
	 * @param order
	 */
	@POST
	@Path("/categories")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postProductCategory(ProductCategory productCategory) {
		productCategoryService.postProductCategory(productCategory);
	}
	
	/**
	 * Web service that put product category
	 * @param order
	 */
	@PUT
	@Path("/categories")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putProductCategory(ProductCategory productCategory) {
		productCategoryService.putProductCategory(productCategory);
	}
	
	/**
	 * Web service that delete product category
	 * @param order
	 */
	@DELETE
	@Path("/categories")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteProductCategory(ProductCategory productCategory) {
		productCategoryService.deleteProductCategory(productCategory);
	}
	
	/**
	 * Web service that get products descriptions
	 * @return
	 */
	@GET
	@Path("/descriptions")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ProductDescription> getProductDescriptions() {
		return productDescriptionService.getProductDescriptions();
	}
	
	/**
	 * Web service that get product description by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/descriptions/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProductDescription getProductDescriptionById(@PathParam("id") int id)
	{
		return productDescriptionService.getProductDescriptionById(id);
	}
	
	@POST
	@Path("/descriptions/product")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProductDescription getProductDescriptionByProductId(Product product)
	{
		return productDescriptionService.getProductDescriptionByProductId(product);
	}
	
	/**
	 * Web service that post product description
	 * @param order
	 */
	@POST
	@Path("/descriptions")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postProductDescription(ProductDescription productDescription) {
		productDescriptionService.postProductDescription(productDescription);
	}
	
	/**
	 * Web service that put product description
	 * @param order
	 */
	@PUT
	@Path("/descriptions")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putProductDescription(ProductDescription productDescription) {
		productDescriptionService.putProductDescription(productDescription);
	}
	
	/**
	 * Web service that delete product description
	 * @param order
	 */
	@DELETE
	@Path("/descriptions")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteProductDescription(ProductDescription productDescription) {
		productDescriptionService.deleteProductDescription(productDescription);
	}
	
	/**
	 * Web service that get products features
	 * @return
	 */
	@GET
	@Path("/features")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ProductFeature> getProductFeatures() {
		return productFeatureService.getProductFeatures();
	}
	
	/**
	 * Web service that get product feature by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/features/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProductFeature getProductFeatureById(@PathParam("id") int id)
	{
		return productFeatureService.getProductFeatureById(id);
	}
	
	@POST
	@Path("/features/product")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String,List<ProductFeature>> getProductFeaturesByProductId(Product product)
	{
		return productFeatureService.getProductFeatureByProductId(product);
	}
	
	/**
	 * Web service that post product feature
	 * @param order
	 */
	@POST
	@Path("/features")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postProductFeature(ProductFeature productFeature) {
		productFeatureService.postProductFeature(productFeature);
	}
	
	/**
	 * Web service that put product feature
	 * @param order
	 */
	@PUT
	@Path("/features")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putProductFeature(ProductFeature productFeature) {
		productFeatureService.putProductFeature(productFeature);
	}
	
	/**
	 * Web service that delete product feature
	 * @param order
	 */
	@DELETE
	@Path("/features")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteProductFeature(ProductFeature productFeature) {
		productFeatureService.deleteProductFeature(productFeature);
	}
	
	/**
	 * Web service that get products imageries
	 * @return
	 */
	@GET
	@Path("/images")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ProductImagery> getProductImagery() {
		return productImageryService.getProductImageries();
	}
	
	/**
	 * Web service that get product imagery by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/images/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProductImagery getProductImageryById(@PathParam("id") int id)
	{
		return productImageryService.getProductImageryById(id);
	}
	
	@POST
	@Path("/images/product")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProductImagery getProductImageryByProductId(Product product)
	{
		return productImageryService.getProductImageryByProductId(product);
	}
	
	/**
	 * Web service that post product imagery
	 * @param order
	 */
	@POST
	@Path("/images")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postProductImagery(ProductImagery productImagery) {
		productImageryService.postProductImagery(productImagery);
	}
	
	/**
	 * Web service that put product imagery
	 * @param order
	 */
	@PUT
	@Path("/images")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putProductImagery(ProductImagery productImagery) {
		productImageryService.putProductImagery(productImagery);
	}
	
	/**
	 * Web service that delete product imagery
	 * @param order
	 */
	@DELETE
	@Path("/images")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteProductImagery(ProductImagery productImagery) {
		productImageryService.deleteProductImagery(productImagery);
	}
	
	/**
	 * Web service that get products presentations
	 * @return
	 */
	@GET
	@Path("/presentations")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ProductPresentation> getProductPresentations() {
		return productPresentationService.getProductPresentations();
	}
	
	/**
	 * Web service that get product presentation by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/presentations/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProductPresentation getProductPresentationById(@PathParam("id") int id)
	{
		return productPresentationService.getProductPresentationById(id);
	}
	
	@POST
	@Path("/presentations/product")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ProductPresentation> getProductPresentationByProductId(Product product)
	{
		return productPresentationService.getProductPresentationByProductId(product);
	}
	
	/**
	 * Web service that post product presentation
	 * @param order
	 */
	@POST
	@Path("/presentations")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postProductPresentation(ProductPresentation productPresentation) {
		productPresentationService.postProductPresentation(productPresentation);
	}
	
	/**
	 * Web service that put product presentation
	 * @param order
	 */
	@PUT
	@Path("/presentations")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putProductPresentation(ProductPresentation productPresentation) {
		productPresentationService.putProductPresentation(productPresentation);
	}
	
	/**
	 * Web service that delete product presentation
	 * @param order
	 */
	@DELETE
	@Path("/presentations")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteProductPresentation(ProductPresentation productPresentation) {
		productPresentationService.deleteProductPresentation(productPresentation);
	}
	
	/**
	 * Web service that get availability stocks
	 * @return
	 */
	@GET
	@Path("/stocks")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<AvailabilityStock> getAvailabilityStocks() {
		return availabilityStockService.getAvailabilityStocks();
	}
	
	/**
	 * Web service that get availability stock by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/stocks/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public AvailabilityStock getAvailabilityStockById(@PathParam("id") int id)
	{
		return availabilityStockService.getAvailabilityStockById(id);
	}
	
	/**
	 * Web service that post availability stock
	 * @param order
	 */
	@POST
	@Path("/stocks")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postAvailabilityStock(AvailabilityStock availabilityStock) {
		availabilityStockService.postAvailabilityStock(availabilityStock);
	}
	
	/**
	 * Web service that put availability stock
	 * @param order
	 */
	@PUT
	@Path("/stocks")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putAvailabilityStock(AvailabilityStock availabilityStock) {
		availabilityStockService.putAvailabilityStock(availabilityStock);
	}
	
	/**
	 * Web service that delete availability stock
	 * @param order
	 */
	@DELETE
	@Path("/stocks")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteAvailabilityStock(AvailabilityStock availabilityStock) {
		availabilityStockService.deleteAvailabilityStock(availabilityStock);
	}
}