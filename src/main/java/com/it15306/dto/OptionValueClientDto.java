package com.it15306.dto;

public class OptionValueClientDto {
	private Integer  value_id;
	private String value_name;
	private Integer option_id;
	
	public String getValue_name() {
		return value_name;
	}

	public void setValue_name(String value_name) {
		this.value_name = value_name;
	}

	public Integer getOption_id() {
		return option_id;
	}

	public void setOption_id(Integer option_id) {
		this.option_id = option_id;
	}

	public Integer getValue_id() {
		return value_id;
	}

	public void setValue_id(Integer value_id) {
		this.value_id = value_id;
	}
	
}
