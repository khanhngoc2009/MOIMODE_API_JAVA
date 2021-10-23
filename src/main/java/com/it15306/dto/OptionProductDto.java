package com.it15306.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.it15306.entities.Options;
import com.it15306.entities.Product;

public class OptionProductDto {
	private OptionDTO option = new OptionDTO();
	
	private ProductDTO product = new ProductDTO();

	public OptionDTO getOption() {
		return option;
	}

	public void setOption(OptionDTO option) {
		this.option = option;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	
}
