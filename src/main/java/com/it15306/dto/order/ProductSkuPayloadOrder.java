package com.it15306.dto.order;

import com.it15306.dto.product.ProductSkuDto;

public class ProductSkuPayloadOrder extends ProductSkuDto{
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
