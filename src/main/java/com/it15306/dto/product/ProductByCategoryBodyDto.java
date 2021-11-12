package com.it15306.dto.product;

public class ProductByCategoryBodyDto {

	private Integer category_id;
	private Integer page;
	private Integer take;
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTake() {
		return take;
	}
	public void setTake(Integer take) {
		this.take = take;
	}
	
	
}
