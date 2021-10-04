package com.it15306.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.OptionProduct;
//import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.repository.OptionProductRepository;


public class OptionProductServiceImpl implements com.it15306.services.OptionProductService {


	@Autowired
	private OptionProductRepository optionProductRepository;
	
	@Override
	public List<OptionProduct> getAllOptionProductByProduct(Product product) {
		// TODO Auto-generated method stub
		return optionProductRepository.findAllOptionProductByProduct(product);
	}

	@Override
	public OptionProduct getById(Integer option_id) {
		// TODO Auto-generated method stub
		Optional<OptionProduct> valueRs = optionProductRepository.findById(option_id);
		return null;
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
