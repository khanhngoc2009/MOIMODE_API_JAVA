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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_sku")
@Getter
@Setter
public class Product_Sku {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_sku_id")
	private Integer product_sku_id;
	
	private String value_sku;
	
	@Basic
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date;
	
	private double price;
	private int quantity_remain;
	private int quantiy_rest;
	private int quantity_total;
	private String url_media;
	private int status;
	private int id_image;
	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product = new Product();

	@OneToMany(mappedBy = "product_sku")
	private List<Sku> sku = new ArrayList<>();

	public Integer getProduct_sku_id() {
		return product_sku_id;
	}


	public List<Sku> getSku() {
		return sku;
	}


	public void setSku(List<Sku> sku) {
		this.sku = sku;
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


	


	public int getQuantiy_rest() {
		return quantiy_rest;
	}


	public void setQuantiy_rest(int quantiy_rest) {
		this.quantiy_rest = quantiy_rest;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
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


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getId_image() {
		return id_image;
	}


	public void setId_image(int id_image) {
		this.id_image = id_image;
	}
	
	
}
