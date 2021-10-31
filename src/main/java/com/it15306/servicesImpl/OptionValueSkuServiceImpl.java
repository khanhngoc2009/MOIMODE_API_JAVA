package com.it15306.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.entities.Option_Sku_Value;
import com.it15306.repository.OptionSkuValueRepository;

@Service("OptionValueSkuServiceImpl")
public class OptionValueSkuServiceImpl {

	
	@Autowired OptionSkuValueRepository optionSkuValueRepository;
	
	public Option_Sku_Value save(Option_Sku_Value op) {
		return optionSkuValueRepository.save(op);
	}
	
}
