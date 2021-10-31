package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.entities.Product_Sku;
import com.it15306.repository.ProductRepository;
import com.it15306.repository.ProductSkuRepository;
import com.it15306.repository.SkuRepository;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements com.it15306.services.ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SkuRepository skuRepository;
	
	@Autowired
	private ProductSkuRepository productSkuRepository;
	
	public List<Object> getAllProducts(int page,int take) {
		Pageable paging =  PageRequest.of(page, take);
        Page<Object> pagedResult = productRepository.findAllProduct(paging);
       
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Object>();
		}
	}
	
	public List<Object> getSellingProducts(int page,int take) {
		Pageable paging =  PageRequest.of(page, take,Sort.by("create_date"));
        Page<Object> pagedResult = productRepository.findProductSelling(paging);
      
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Object>();
		}
	}
	
	public List<Product> getAllProductsAdmin(int page,int take) {
		Pageable paging =  PageRequest.of(page, take,Sort.by("create_date"));
        Page<Product> pagedResult = productRepository.findAllProductsAdmin(paging);
       
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Product>();
		}
	}
	
	public Object getByIdProduct(Integer product_id) {
		return productRepository.findByIdProduct(product_id); 
	}
	
	public Product getById(Integer product_id) {
		
		Optional<Product> op_Res = productRepository.findById(product_id); 
		
		return op_Res.get();
	}
	
	public long getCountClient() {
		return productRepository.countProductClient();
	}
	
	public long getCountAdmin() {
		return productRepository.countProductAdmin(); 
	}
	

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}

	public List<Object> getProductByCategory(Category category,int page,int take){
		Pageable paging =  PageRequest.of(page, take);
        Page<Object> pagedResult = productRepository.findProductByCategory(category,paging);
        
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Object>();
		}
	}
	
	public Object findBySku(Integer product_is, Integer option_1,Integer option_2,Integer option_3 ) {
		return skuRepository.findByOptionValue(product_is, option_1, option_2, option_3);
	}
	
	public Product_Sku saveProductSku(Product_Sku p_u) {
		return productSkuRepository.save(p_u);
	}
}


















