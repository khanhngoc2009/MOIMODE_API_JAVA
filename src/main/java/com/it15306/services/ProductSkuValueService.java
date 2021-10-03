package com.it15306.services;

import java.util.List;

import com.it15306.entities.ProductSkuValues;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface ProductSkuValueService {
	List<ProductSkuValues> getAllProductSkuValues();
	ProductSkuValues getByIdProductSkuValues(String id);
	ProductSkuValues saveProductSkuValues(ProductSkuValues productSkuValues);
	void delete(String id);
}
