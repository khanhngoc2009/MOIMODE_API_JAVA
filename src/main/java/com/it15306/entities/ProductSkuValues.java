package com.it15306.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ProductSkuValues")
@Getter
@Setter
@Component
public class ProductSkuValues {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_sku_id")
	private Integer product_sku_id;
	

	private String SKU_value;
	private Integer option_value_id_1;
	private String option_value_name_1;
	private Integer option_value_id_2;
	private String option_value_name_2;
	private Integer option_value_id_3;
	private String option_value_name_3;
	
	@Basic
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date;
	
	private double price;
	
	private Integer quantity_sold;
	private Integer quantity_rest;
	private String url_media;
	
	
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product = new Product();

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
}
