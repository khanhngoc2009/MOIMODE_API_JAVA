package com.it15306.dto.product;

import java.util.List;

public class PayloadUpdateProductDto {
	private ProductDTO product;
	private  List<ProductSkuDto> listSKU;
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public List<ProductSkuDto> getListSKU() {
		return listSKU;
	}
	public void setListSKU(List<ProductSkuDto> listSKU) {
		this.listSKU = listSKU;
	}
	
}
