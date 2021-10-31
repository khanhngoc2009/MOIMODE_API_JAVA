package com.it15306.dto.option;

import java.util.List;

public class CreateOptionDto {
	private String name;
//	private String description;
	private List<String> value;
	private Integer optionTypeId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	public Integer getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Integer optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	
}
