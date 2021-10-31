package com.it15306.dto.product;

import java.util.List;

import com.it15306.dto.option.OptionDTO;
import com.it15306.dto.option.OptionValueDTO;

public class DataCreateProductDtos {
	private DataProductBodyDtos product;
	private List<Integer> listOption;
	public DataProductBodyDtos getProduct() {
		return product;
	}
	public void setProduct(DataProductBodyDtos product) {
		this.product = product;
	}
	public List<Integer> getListOption() {
		return listOption;
	}
	public void setListOption(List<Integer> listOption) {
		this.listOption = listOption;
	}
	
}
