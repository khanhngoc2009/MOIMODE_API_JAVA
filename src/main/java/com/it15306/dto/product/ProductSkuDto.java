package com.it15306.dto.product;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.it15306.entities.Product;

public class ProductSkuDto {
private Integer product_sku_id;
	
	private String value_sku;
	
	
	private Date create_date;
	
	private double price;
	private int quantity_remain;
	private int quantiy_rest;
	private int quantity_total;
	private String url_media;
	private int status;
	private int id_image;
	public Integer getProduct_sku_id() {
		return product_sku_id;
	}
	public void setProduct_sku_id(Integer product_sku_id) {
		this.product_sku_id = product_sku_id;
	}
	public String getValue_sku() {
		return value_sku;
	}
	public void setValue_sku(String value_sku) {
		this.value_sku = value_sku;
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
	public int getQuantity_remain() {
		return quantity_remain;
	}
	public void setQuantity_remain(int quantity_remain) {
		this.quantity_remain = quantity_remain;
	}
	
	public int getQuantity_total() {
		return quantity_total;
	}
	public void setQuantity_total(int quantity_total) {
		this.quantity_total = quantity_total;
	}
	public String getUrl_media() {
		return url_media;
	}
	public void setUrl_media(String url_media) {
		this.url_media = url_media;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getQuantiy_rest() {
		return quantiy_rest;
	}
	public void setQuantiy_rest(int quantiy_rest) {
		this.quantiy_rest = quantiy_rest;
	}
	/**
	 * @return the id_image
	 */
	public int getId_image() {
		return id_image;
	}
	/**
	 * @param id_image the id_image to set
	 */
	public void setId_image(int id_image) {
		this.id_image = id_image;
	}
	
}
