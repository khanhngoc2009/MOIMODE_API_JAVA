package com.it15306.dto.option_value;

import java.util.List;

import com.it15306.dto.option.OptionValueDTO;

public class BodyCreateOptionValue {

	private Integer option_id;
	private List<OptionValueAdminDto> listValue;
	public Integer getOption_id() {
		return option_id;
	}
	public void setOption_id(Integer option_id) {
		this.option_id = option_id;
	}
	public List<OptionValueAdminDto> getListValue() {
		return listValue;
	}
	public void setListValue(List<OptionValueAdminDto> listValue) {
		this.listValue = listValue;
	}
}
