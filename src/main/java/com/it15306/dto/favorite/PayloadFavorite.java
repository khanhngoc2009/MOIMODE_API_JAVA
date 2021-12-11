package com.it15306.dto.favorite;

import com.it15306.entities.User;

public class PayloadFavorite {
	private Integer id_product;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId_product() {
		return id_product;
	}

	public void setId_product(Integer id_product) {
		this.id_product = id_product;
	}
}
