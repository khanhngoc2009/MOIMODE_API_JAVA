package com.it15306.servicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.repository.ProductRepository;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements com.it15306.services.ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product getByIdProduct(String product_id) {
		// TODO Auto-generated method stub
		return productRepository.findById(product_id);
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
	public List<Product> getProductByCategory(Category category){
		return productRepository.findProductByCategory(category);
	}
	
}
