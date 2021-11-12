package com.it15306.services;

import java.util.List;
import java.util.Optional;

import com.it15306.entities.Options;
import com.it15306.entities.Product;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface OptionProductService {
	List<Options> getOptions(Integer page,Integer take);
	Options getById(Integer option_id);
	Options saveOptionProduct(Options option);
	void delete(Options option);
}
