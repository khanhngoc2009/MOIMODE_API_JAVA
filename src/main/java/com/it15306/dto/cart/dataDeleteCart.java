package com.it15306.dto.cart;

import java.util.List;

public class dataDeleteCart {
	private Integer userId;
	private List<String>  productSKU;
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the productSKU
	 */
	public List<String> getProductSKU() {
		return productSKU;
	}
	/**
	 * @param productSKU the productSKU to set
	 */
	public void setProductSKU(List<String> productSKU) {
		this.productSKU = productSKU;
	}
	
}
