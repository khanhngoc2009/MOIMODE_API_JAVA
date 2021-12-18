package com.it15306.dto.order;

import com.it15306.dto.product.ProductSkuDto;

public class ObjectCreateOrderDto {
	private ProductSkuDto sku;
	private Integer quantity;
	public ProductSkuDto getSku() {
		return sku;
	}
	public void setSku(ProductSkuDto sku) {
		this.sku = sku;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
}
