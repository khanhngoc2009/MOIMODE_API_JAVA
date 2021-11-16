package com.it15306.dto.option;

import java.util.List;

import com.it15306.dto.option_value.OptionValueAdminDto;

public class CreateOptionDto {
	private String name;
//	private String description;
	private List<OptionValueAdminDto> values;
	private Integer type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<OptionValueAdminDto> getValues() {
		return values;
	}
	public void setValues(List<OptionValueAdminDto> values) {
		this.values = values;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
