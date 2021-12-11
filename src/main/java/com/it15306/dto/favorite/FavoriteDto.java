package com.it15306.dto.favorite;

import java.util.Date;

import com.it15306.dto.UserDTO;
import com.it15306.dto.product.ProductDTO;

public class FavoriteDto {
	private Integer id;
	private Integer status;
	private Date date;
	private ProductDTO product;
	private UserDTO user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	
}
