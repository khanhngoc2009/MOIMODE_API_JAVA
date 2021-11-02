package com.it15306.dto.product;

import java.util.List;

import com.it15306.dto.option.OptionDTO;
import com.it15306.dto.option.OptionValueDTO;

public class DataCreateProductDtos {
	private DataProductBodyDtos product;
	private List<OptionDTO> listOption;
	public DataProductBodyDtos getProduct() {
		return product;
	}
	public void setProduct(DataProductBodyDtos product) {
		this.product = product;
	}
	public List<OptionDTO> getListOption() {
		return listOption;
	}
	public void setListOption(List<OptionDTO> listOption) {
		this.listOption = listOption;
	}

	
}
