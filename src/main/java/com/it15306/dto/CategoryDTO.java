package com.it15306.dto;

import java.util.Date;



public class CategoryDTO {

	private Integer category_id;
	
	private Date create_date;
	
	private Integer status;
	private Integer category_parent_id;
	
	private Integer type;
	private String category_name;
	private String description;
	private String url_image;
	

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCategory_parent_id() {
		return category_parent_id;
	}

	public void setCategory_parent_id(Integer category_parent_id) {
		this.category_parent_id = category_parent_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUr_image() {
		return url_image;
	}

	public void setUr_image(String url_image) {
		this.url_image = url_image;
	}



}
