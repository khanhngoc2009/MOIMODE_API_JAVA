package com.it15306.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "product_order")
@Getter
@Setter
@Component
public class ProductOrder {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Basic
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date;
	
	private String product_name;
	private String properties;
	private Integer status;
	private Double price;
	private String image;
	private Integer quantity;
	private Integer product_id;
	private Integer sku_id;
	
	public Integer getSku_id() {
		return sku_id;
	}


	public void setSku_id(Integer sku_id) {
		this.sku_id = sku_id;
	}


	public Integer getProduct_id() {
		return product_id;
	}


	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}


	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order = new Order();


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public String getProperties() {
		return properties;
	}


	public void setProperties(String properties) {
		this.properties = properties;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
