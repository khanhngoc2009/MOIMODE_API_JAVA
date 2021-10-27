package com.it15306.services;

import java.util.List;

import com.it15306.entities.OptionValue;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface OptionValueService {
	List<OptionValue> getAllOptionValueByOption(Integer option_id);
	OptionValue getById(Integer value_id);

	OptionValue saveOptionValue(OptionValue optionValue);

	void delete(Integer id);
}
