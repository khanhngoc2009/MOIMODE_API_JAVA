package com.it15306.dto.favorite;

public class PayloadList {
	private Integer take;
	private Integer page;
	private Integer product_id;
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getTake() {
		return take;
	}
	public void setTake(Integer take) {
		this.take = take;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
}
