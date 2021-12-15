package com.it15306.dto.product;

import com.it15306.dto.PageDto;

public class PayloadProductSkuAdmin extends PageDto{
	private String sku_value;

	public String getSku_value() {
		return sku_value;
	}

	public void setSku_value(String sku_value) {
		this.sku_value = sku_value;
	}
	
}
