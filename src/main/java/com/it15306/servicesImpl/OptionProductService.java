package com.it15306.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.OptionProduct;
//import com.it15306.entities.Category;

import com.it15306.repository.OptionProductRepository;


public class OptionProductService implements com.it15306.services.OptionProductService {

	@Autowired
	private OptionProductRepository optionProductRepository;
	
	@Override
	public List<OptionProduct> getAllOptionProductByProduct(String product_id) {
		// TODO Auto-generated method stub
		return optionProductRepository.findAllOptionProductByProductId(product_id);
	}

	@Override
	public Optional<OptionProduct> getById(Integer option_id) {
		// TODO Auto-generated method stub
		
		return optionProductRepository.findById(option_id);
	}

	@Override
	public OptionProduct saveOptionProduct(OptionProduct option) {
		// TODO Auto-generated method stub
		return optionProductRepository.save(option);
	}

	@Override
	public void delete(OptionProduct option) {
		optionProductRepository.delete(option);
		
	}
	
}
