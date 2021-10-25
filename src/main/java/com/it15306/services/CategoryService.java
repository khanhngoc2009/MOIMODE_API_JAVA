package com.it15306.services;

import java.util.Date;
import java.util.List;

import com.it15306.entities.Category;
import com.it15306.entities.Product;

public interface CategoryService {
	List<Category> getAllCategoryByType(int type,int page,int take);
	List<Category> getAllCategoryByCategory(int category_parent_id,int type,int page,int take);
	List<Category> getAllCategoryByCreateDate(Date ngay_bat_dau,Date ngay_ket_thuc);
	List<Product> getListProductByCategory(String category_id, int type);
	Category saveCategory(Category category);
	void delete(Category category);
}
