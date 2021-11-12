package com.it15306.dto.product;

import java.util.List;

public class ListSkuCreateDto {

	private Integer product_id;
	private List<String> list_sku;
	private List<String> list_id;
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public List<String> getList_sku() {
		return list_sku;
	}
	public void setList_sku(List<String> list_sku) {
		this.list_sku = list_sku;
	}
	public List<String> getList_id() {
		return list_id;
	}
	public void setList_id(List<String> list_id) {
		this.list_id = list_id;
	}
	
	
}
