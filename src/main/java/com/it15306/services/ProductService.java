package com.it15306.services;

import java.util.List;

import com.it15306.entities.Product;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface ProductService {
//	List<Product> getAllProduct();
//	Object getByIdProduct(Integer product_id);
	Product saveProduct(Product product);
	void delete(Product product);
}
