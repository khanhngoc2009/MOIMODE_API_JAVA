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

import com.it15306.entities.Options;
//import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.repository.OptionsRepository;
import com.it15306.services.OptionProductService;

@Service("OptionProductServiceImpl")
public class OptionsServiceImpl implements OptionProductService{


	@Autowired
	private OptionsRepository optionProductRepository;



	@Override
	public Options getById(Integer option_id) {
		// TODO Auto-generated method stub
		return optionProductRepository.getOne(option_id);
	}
	
	
	public boolean checkOptionExist(Integer option_id) {
		// TODO Auto-generated method stub
		return optionProductRepository.existsById(option_id);
	}

	@Override
	public Options saveOptionProduct(Options option) {
		// TODO Auto-generated method stub
		return optionProductRepository.save(option);
	}

	@Override
	public void delete(Options option) {
		 optionProductRepository.delete(option);
	}
	
	@Override
	public List<Options> getOptions(Integer page,Integer take) {
		// TODO Auto-generated method stub
		return optionProductRepository.findAll();
		
	}
}
