package com.it15306.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.it15306.entities.OptionValue;

public class OptionDTO {	
	private String name;
	private String description;
	private List<OptionValueClientDto> option_values = new ArrayList<>();
	
	public List<OptionValueClientDto> getOption_values() {
		return option_values;
	}

	public void setOption_values(List<OptionValueClientDto> option_values) {
		this.option_values = option_values;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
