package com.it15306.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.it15306.entities.Product;
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
@Table(name = "Category")

public class Category {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer id;
	
	@Basic
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date;
	
	private Integer status;
	private Integer category_parent_id;
	
	private Integer type;
	private String category_name;
	private String description;
	@Column(name = "url_image")
	private String image;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<>();

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
}
