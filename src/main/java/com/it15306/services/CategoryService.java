package com.it15306.services;

import java.util.List;

import com.it15306.entities.Category;
import com.it15306.entities.Product;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface CategoryService {
	List<Category> getAllCategoryByType(int type);
	List<Category> getAllCategoryByCategory(int category_parent_id,int type);
	List<Product> getListProductByCategory(String category_id, int type);
	Category saveCategory(Category category);
	void delete(Category category);
}
