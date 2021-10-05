package com.it15306.services;

import java.util.List;

import com.it15306.entities.Product;
import com.it15306.entities.ProductSkuValues;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface ProductSkuValueService {
	List<ProductSkuValues> getAllProductSkuValues(Integer option_id_1,Integer option_id_2,Integer option_id_3, Product product );
	ProductSkuValues getByIdProductSkuValues(Integer id);
	ProductSkuValues saveProductSkuValues(ProductSkuValues productSkuValues);
	void delete(ProductSkuValues productSkuValues);
}
