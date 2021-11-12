package com.it15306.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.entities.Options;
import com.it15306.entities.OptionValue;
import com.it15306.entities.Product;
//import com.it15306.entities.Category;
import com.it15306.entities.User;
import com.it15306.repository.OptionValueRepository;
import com.it15306.repository.OptionsRepository;
import com.it15306.repository.ProductRepository;
import com.it15306.services.OptionProductService;
import com.it15306.services.OptionValueService;

@Service("OptionValueServiceImpl")
public class OptionValueServiceImpl implements OptionValueService {
	
	@Autowired private OptionValueRepository optionValueRepository;
	
	@Override
	public List<OptionValue> getAllOptionValueByOption(Options option) {
		// TODO Auto-generated method stub
		return optionValueRepository.findAllOptionValue(option);
	}

	@Override
	public OptionValue getById(Integer value_id) {
		// TODO Auto-generated method stub
		return optionValueRepository.getOne(value_id);
	}

	@Override
	public OptionValue saveOptionValue(OptionValue optionValue) {
		// TODO Auto-generated method stub
		return optionValueRepository.save(optionValue);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		optionValueRepository.deleteById(id);
	}
	
	
	public String deleteByOption(Options option) {
		// TODO Auto-generated method stub
		return optionValueRepository.deleteByOption(option);
	}
	
	
}
