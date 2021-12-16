package com.it15306.dto.product;

public class ProductByCategoryBodyDto {

	private Integer category_id;
	private Integer min_price;
	private Integer max_price;
	private Integer type_filter;
	
	public Integer getMin_price() {
		return min_price;
	}
	public void setMin_price(Integer min_price) {
		this.min_price = min_price;
	}
	public Integer getMax_price() {
		return max_price;
	}
	public void setMax_price(Integer max_price) {
		this.max_price = max_price;
	}
	public Integer getType_filter() {
		return type_filter;
	}
	public void setType_filter(Integer type_filter) {
		this.type_filter = type_filter;
	}
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
