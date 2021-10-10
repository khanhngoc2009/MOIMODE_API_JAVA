package com.it15306.dto.product;

import java.util.List;

import com.it15306.dto.OptionDTO;
import com.it15306.dto.OptionValueDTO;
import com.it15306.dto.ProductDTO;

public class dataCreateProductDto {
	private dataProductBodyDto product;
	private List<OptionDTO> options;
	private List<OptionValueDTO> values;
	public dataProductBodyDto getProduct() {
		return product;
	}
	public void setProduct(dataProductBodyDto product) {
		this.product = product;
	}
	public List<OptionDTO> getOptions() {
		return options;
	}
	public void setOptions(List<OptionDTO> options) {
		this.options = options;
	}
	public List<OptionValueDTO> getValues() {
		return values;
	}
	public void setValues(List<OptionValueDTO> values) {
		this.values = values;
	}
	
}
