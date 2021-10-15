package com.it15306.dto;

import java.util.Date;

public class ProductSkuValueDto {
private Integer product_sku_id;
	

	private String SKU_value;
	private Integer option_value_id_1;
	private String option_value_name_1;
	private Integer option_value_id_2;
	private String option_value_name_2;
	private Integer option_value_id_3;
	private String option_value_name_3;
	
	private Date create_date;
	
	private double price;
	
	private Integer quantity_sold;
	private Integer quantity_rest;
	private String url_media;
	
	
	private Integer status;
	
	private ProductDTO product;

	public Integer getProduct_sku_id() {
		return product_sku_id;
	}

	public void setProduct_sku_id(Integer product_sku_id) {
		this.product_sku_id = product_sku_id;
	}

	public String getSKU_value() {
		return SKU_value;
	}

	public void setSKU_value(String sKU_value) {
		SKU_value = sKU_value;
	}

	public Integer getOption_value_id_1() {
		return option_value_id_1;
	}

	public void setOption_value_id_1(Integer option_value_id_1) {
		this.option_value_id_1 = option_value_id_1;
	}

	public String getOption_value_name_1() {
		return option_value_name_1;
	}

	public void setOption_value_name_1(String option_value_name_1) {
		this.option_value_name_1 = option_value_name_1;
	}

	public Integer getOption_value_id_2() {
		return option_value_id_2;
	}

	public void setOption_value_id_2(Integer option_value_id_2) {
		this.option_value_id_2 = option_value_id_2;
	}

	public String getOption_value_name_2() {
		return option_value_name_2;
	}

	public void setOption_value_name_2(String option_value_name_2) {
		this.option_value_name_2 = option_value_name_2;
	}

	public Integer getOption_value_id_3() {
		return option_value_id_3;
	}

	public void setOption_value_id_3(Integer option_value_id_3) {
		this.option_value_id_3 = option_value_id_3;
	}

	public String getOption_value_name_3() {
		return option_value_name_3;
	}

	public void setOption_value_name_3(String option_value_name_3) {
		this.option_value_name_3 = option_value_name_3;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getQuantity_sold() {
		return quantity_sold;
	}

	public void setQuantity_sold(Integer quantity_sold) {
		this.quantity_sold = quantity_sold;
	}

	public Integer getQuantity_rest() {
		return quantity_rest;
	}

	public void setQuantity_rest(Integer quantity_rest) {
		this.quantity_rest = quantity_rest;
	}

	public String getUrl_media() {
		return url_media;
	}

	public void setUrl_media(String url_media) {
		this.url_media = url_media;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
}
