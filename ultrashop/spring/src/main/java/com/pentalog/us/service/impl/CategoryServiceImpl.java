package com.pentalog.us.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pentalog.us.dao.CategoryDAO;
import com.pentalog.us.model.Category;
import com.pentalog.us.service.CategoryService;

/**
 * The category service implementation
 * @author acozma and bpopovici
 *
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	/**
	 * The category data access object 
	 */
	@Autowired
	CategoryDAO categoryDao;

	/**
	 * @see {@link CategoryService.getCategoryById}
	 */
	@Override
	public Category getCategoryById(int id) {
		return categoryDao.findOne(id);
	}

	/**
	 * @see {@link CategoryService.getCategories}
	 */
	@Override
	public List<Category> getCategories() {
		return categoryDao.findAll();
	}

	/**
	 * @see {@link CategoryService.putCategory}
	 */
	@Override
	public void putCategory(Category category) {
		categoryDao.save(category);
	}

	/**
	 * @see {@link CategoryService.postCategory}
	 */
	@Override
	public void postCategory(Category category) {
		categoryDao.save(category);
	}

	/**
	 * @see {@link CategoryService.deleteCategory}
	 */
	@Override
	public void deleteCategory(Category category) {
		categoryDao.delete(category);
	}

	@Override
	public List<Category> getCategoryByParent(int parent) {
		return categoryDao.findByParent(parent);
	}

	@Override
	public Map<String, List<Category>> getCategoriesMap() {
		Map<String, List<Category>> categoryMap = new HashMap<String, List<Category>>();
		List<Category> parentCategories = getCategoryByParent(0);
		for(Category c : parentCategories) {
			List<Category> childrenCategories = getCategoryByParent(c.getId());
			categoryMap.put(c.getName(), childrenCategories);
		}
		return categoryMap;
	}
}