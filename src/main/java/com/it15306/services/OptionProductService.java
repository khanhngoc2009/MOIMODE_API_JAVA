package com.it15306.services;

import java.util.List;
import java.util.Optional;

import com.it15306.entities.OptionProduct;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface OptionProductService {
	List<OptionProduct> getAllOptionProductByProduct(String product_id);
	Optional<OptionProduct> getById(Integer option_id);
	OptionProduct saveOptionProduct(OptionProduct option);
	void delete(OptionProduct option);
}
