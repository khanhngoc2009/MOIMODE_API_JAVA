package com.it15306.dto.cart;

public class dataBodyCart {
	private Integer quantity;
	private Integer userId;
	private Integer productSKUId;
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
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the productSKUId
	 */
	public Integer getProductSKUId() {
		return productSKUId;
	}
	/**
	 * @param productSKUId the productSKUId to set
	 */
	public void setProductSKUId(Integer productSKUId) {
		this.productSKUId = productSKUId;
	}
	
	
	
}
