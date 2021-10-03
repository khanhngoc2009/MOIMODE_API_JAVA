package com.it15306.services;

import java.util.List;

import com.it15306.entities.Category;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface CategoryService {
	List<Category> getAllCategory();
	List<Category> getAllCategoryByCategory(String category_parent_id);
	
	
	void delete(String id);
}
