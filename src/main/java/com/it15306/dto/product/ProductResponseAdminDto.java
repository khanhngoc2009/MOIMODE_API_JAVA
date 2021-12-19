package com.it15306.dto.product;

import java.util.Date;

public class ProductResponseAdminDto {
	private Integer id;

	private Date create_date;
	
	private String product_name;
	private String description;
	private Integer status;
	private Integer type;
	private String category_name;
	private String url_media;
	
	public String getUrl_media() {
		return url_media;
	}

	public void setUrl_media(String url_media) {
		this.url_media = url_media;
	}

	private Double min_price;
	private Double max_price;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getMin_price() {
		return min_price;
	}

	public void setMin_price(Double min_price) {
		this.min_price = min_price;
	}

	public Double getMax_price() {
		return max_price;
	}

	public void setMax_price(Double max_price) {
		this.max_price = max_price;
	}
}
