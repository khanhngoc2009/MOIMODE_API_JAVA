package com.it15306.servicesImpl;

import java.util.List;

import com.it15306.entities.OptionValue;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface OptionValueService {
	List<OptionValue> getAllOptionValueByOption(String option_id);
	OptionValue getById(String value_id);

	OptionValue saveOptionValue(OptionValue optionValue);

	void delete(String id);
}
