package com.it15306.dto.product;

import java.util.List;

import com.it15306.dto.OptionClientDto;
import com.it15306.dto.OptionDTO;
import com.it15306.dto.OptionValueDTO;
import com.it15306.dto.ProductDTO;
import com.it15306.dto.ProductSkuValueDto;

public class ProductDetailDto {
	private ProductDTO product;
	private List<OptionClientDto> listOption;
	private List<ProductSkuValueDto> listProductSku;
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public List<OptionClientDto> getListOption() {
		return listOption;
	}
	public void setListOption(List<OptionClientDto> listOption) {
		this.listOption = listOption;
	}
	public List<ProductSkuValueDto> getListProductSku() {
		return listProductSku;
	}
	public void setListProductSku(List<ProductSkuValueDto> listProductSku) {
		this.listProductSku = listProductSku;
	}
}
