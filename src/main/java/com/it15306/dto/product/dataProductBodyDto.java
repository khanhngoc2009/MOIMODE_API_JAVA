package com.it15306.dto.product;

import java.util.Date;

public class dataProductBodyDto {
	
	private String product_name;
	private String description;
	private Integer category_id;
	private Integer ware_house_id;
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Integer getWare_house_id() {
		return ware_house_id;
	}
	public void setWare_house_id(Integer ware_house_id) {
		this.ware_house_id = ware_house_id;
	}
	
}
