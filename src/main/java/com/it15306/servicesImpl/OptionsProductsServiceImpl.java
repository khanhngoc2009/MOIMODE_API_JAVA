package com.it15306.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.entities.Option_Product;
import com.it15306.entities.Options;
import com.it15306.repository.OptionProductsRespository;

@Service("OptionsProductsServiceImpl")
public class OptionsProductsServiceImpl {

	
	@Autowired private OptionProductsRespository optionProductsRespository; 
	
	public Option_Product getOption(Options option) {
		
		return optionProductsRespository.findByIdOption(option);
	}

	
}
