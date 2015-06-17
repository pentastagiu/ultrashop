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

import com.pentalog.us.model.Category;
import com.pentalog.us.service.CategoryService;

/**
 * Category web services
 * @authors acozma and bpopovici
 *
 */
@Component
@Path("/categories")
public class CategoryWS {
	
	/**
	 * Category service
	 */
	@Autowired
	CategoryService categoryService;

	/**
	 * Web service that get categories
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Category> getCategories() {
		return categoryService.getCategories();
	}
	
	/**
	 * Web service that get category by id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Category getCategoryById(@PathParam("id") int id)
	{
		return categoryService.getCategoryById(id);
	}
	
	@GET
	@Path("/category/{parent}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Category> getCategoryByParent(@PathParam("parent") int parent)
	{
		return categoryService.getCategoryByParent(parent);
	}
	
	@GET
	@Path("/map")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String,List<Category>> getCategoriesMap()
	{
		return categoryService.getCategoriesMap();
	}
	
	/**
	 * Web service that post category
	 * @param order
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void postCategory(Category category) {
		categoryService.postCategory(category);
	}
	
	/**
	 * Web service that put category
	 * @param order
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void putCategory(Category category) {
		categoryService.putCategory(category);
	}
	
	/**
	 * Web service that delete category
	 * @param order
	 */
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void deleteCategory(Category category) {
		categoryService.deleteCategory(category);
	}
}