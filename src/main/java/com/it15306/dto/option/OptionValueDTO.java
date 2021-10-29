package com.it15306.dto.option;

import com.it15306.entities.Options;

public class OptionValueDTO {
	private Integer id;
	private String value_name;
	private Integer option_id;
	
	public String getValue_name() {
		return value_name;
	}

	public void setValue_name(String value_name) {
		this.value_name = value_name;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOption_id() {
		return option_id;
	}

	public void setOption_id(Integer option_id) {
		this.option_id = option_id;
	}

	
	
}
