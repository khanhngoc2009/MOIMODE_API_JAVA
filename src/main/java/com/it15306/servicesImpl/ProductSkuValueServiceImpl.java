package com.it15306.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.Product;
import com.it15306.entities.ProductSkuValues;
//import com.it15306.entities.Category;
import com.it15306.entities.User;
import com.it15306.repository.ProductRepository;
import com.it15306.repository.ProductSkuValuesRepository;

public class ProductSkuValueServiceImpl implements com.it15306.services.ProductSkuValueService{

	@Autowired
	private ProductSkuValuesRepository productSkuValuesRepository;
	
	@Override
	public List<ProductSkuValues> getAllProductSkuValues(Integer option_id_1,Integer option_id_2,Integer option_id_3, Product product ) {
		// TODO Auto-generated method stub
		return productSkuValuesRepository.findAllProductSkuValues(option_id_1,option_id_2,option_id_3, product);
	}

	@Override
	public ProductSkuValues getByIdProductSkuValues(Integer id) {
		// TODO Auto-generated method stub
		Optional<ProductSkuValues> valueRs =  productSkuValuesRepository.findById(id);
		return null;
	}

	@Override
	public ProductSkuValues saveProductSkuValues(ProductSkuValues productSkuValues) {
		// TODO Auto-generated method stub
		return productSkuValuesRepository.save(productSkuValues);
	}

	@Override
	public void delete(ProductSkuValues productSkuValues) {
		productSkuValuesRepository.delete(productSkuValues);
		
	}
	
}
