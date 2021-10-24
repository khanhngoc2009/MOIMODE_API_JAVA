package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.it15306.dto.ProductDTO;
import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.entities.Product_Sku;
import com.it15306.repository.OptionProductsRespository;
import com.it15306.repository.ProductRepository;
import com.it15306.repository.SkuRepository;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements com.it15306.services.ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OptionProductsRespository optionProductsRespository;
	@Autowired
	private SkuRepository skuRepository;
	
	public List<Object> getAllProducts() {
		// TODO Auto-generated method stub
		List<Object> list = productRepository.findAllProduct();
		
		return list;
	}
	
	public List<Product> getAllProductsAdmin() {
		// TODO Auto-generated method stub
		List<Product> list = productRepository.findAllProductsAdmin();
		
		return list;
	}

//	@Override
	public Object getByIdProduct(Integer product_id) {
		// TODO Auto-generated method stub
		Object p=productRepository.findByIdProduct(product_id); 
		return p;
	}
	

	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		productRepository.delete(product);
//		return ;
	}
//	public List<Product> Search(Date startDate, Date endDate,Integer status,String productname  ) {
//		return productRepository.searchProduct(startDate, endDate, status, productname);
//	}
	public List<Object> getProductByCategory(Category category){
		return productRepository.findProductByCategory(category);
	}
	
	public Object findBySku(Integer product_is, Integer option_1,Integer option_2,Integer option_3 ) {
	return skuRepository.findByOptionValue(product_is, option_1, option_2, option_3);
}
	
}
