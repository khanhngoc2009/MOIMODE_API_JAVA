package com.it15306.services;

import java.util.List;

import com.it15306.entities.Product;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface ProductService {
	List<Product> getAllProduct();
	Product getByIdProduct(String product);
	Product saveProduct(Product product);
	void delete(String product_id);
}
