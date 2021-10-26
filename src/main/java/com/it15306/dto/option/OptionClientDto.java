package com.it15306.dto.option;

import java.util.List;

public class OptionClientDto {
	private Integer option_id;
	private String name;
	private String description;
	private List<OptionValueClientDto> listOptionValue;
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


	public List<OptionValueClientDto> getListOptionValue() {
		return listOptionValue;
	}

	public void setListOptionValue(List<OptionValueClientDto> listOptionValue) {
		this.listOptionValue = listOptionValue;
	}

	public Integer getOption_id() {
		return option_id;
	}

	public void setOption_id(Integer option_id) {
		this.option_id = option_id;
	}


	
}
