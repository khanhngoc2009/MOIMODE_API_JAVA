package com.it15306.dto.option;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OptionResponseDto {
	private Integer id;
	private String name;
	private String description;
	private Integer status;
	private Date create_date;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
}
