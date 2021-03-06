package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.dto.ProductDTO;
import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.repository.ProductRepository;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements com.it15306.services.ProductService {

	@Autowired
	private ProductRepository productRepository;


	public List<Object> getAllProducts() {
		// TODO Auto-generated method stub
		List<Object> list = productRepository.findAllProduct();
		
		return list;
	}

	@Override
	public Object getByIdProduct(Integer product_id) {
		// TODO Auto-generated method stub
		return productRepository.findByIdProduct(product_id);
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
	public List<Product> Search(Date startDate, Date endDate,Integer status,String productname  ) {
		return productRepository.searchProduct(startDate, endDate, status, productname);
	}
	public List<Object> getProductByCategory(Category category){
		return productRepository.findProductByCategory(category);
	}
	
}
