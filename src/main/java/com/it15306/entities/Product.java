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
@Table(name = "Product")
@Getter
@Setter
@Component
public class Product {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer product_id;
	
	@Basic
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date;
	
	private String product_name;
	private String description;
	private Integer status;
	private Integer type;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category = new Category();
	
	@ManyToOne
	@JoinColumn(name = "ware_house_id")
	private Warehouse warehouse = new Warehouse();
	
	@OneToMany(mappedBy = "product")
	private List<OptionProduct> options = new ArrayList<>();
	
	@OneToMany(mappedBy = "product")
	private List<ProductSkuValues> product_sku_values = new ArrayList<>();

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public List<OptionProduct> getOptions() {
		return options;
	}

	public void setOptions(List<OptionProduct> options) {
		this.options = options;
	}

	public List<ProductSkuValues> getProduct_sku_values() {
		return product_sku_values;
	}

	public void setProduct_sku_values(List<ProductSkuValues> product_sku_values) {
		this.product_sku_values = product_sku_values;
	}
	
	
	
	
}
