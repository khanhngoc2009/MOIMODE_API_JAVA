package com.it15306.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.Options;
//import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.repository.OptionProductRepository;


public class OptionProductServiceImpl {


	@Autowired
	private OptionProductRepository optionProductRepository;
//	
//	@Override
//	public List<Options> getAllOptionProductByProduct(Product product) {
//		// TODO Auto-generated method stub
//		return optionProductRepository.findAllOptionProductByProduct(product);
//	}
//
//	@Override
//	public Options getById(Integer option_id) {
//		// TODO Auto-generated method stub
//		Optional<Options> valueRs = optionProductRepository.findById(option_id);
//		return null;
//	}
//
//	@Override
//	public Options saveOptionProduct(Options option) {
//		// TODO Auto-generated method stub
//		return optionProductRepository.save(option);
//	}
//
//	@Override
//	public void delete(Options option) {
//		optionProductRepository.delete(option);
//		
//	}
	
}
