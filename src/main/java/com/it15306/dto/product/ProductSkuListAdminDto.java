package com.it15306.dto.product;

public class ProductSkuListAdminDto extends ProductSkuDto{
	private String product_name;
	private String option_value;
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getOption_value() {
		return option_value;
	}
	public void setOption_value(String option_value) {
		this.option_value = option_value;
	}
}
