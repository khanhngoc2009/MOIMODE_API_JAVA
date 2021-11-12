package com.it15306.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;



@Entity
@Table(name = "cart_product")
public class CartProduct implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "product_sku_id")
	private Product_Sku productSkus;
	
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart carts;
	
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}



	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}



	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	/**
	 * @return the productSkus
	 */
	public Product_Sku getProductSkus() {
		return productSkus;
	}



	/**
	 * @param productSkus the productSkus to set
	 */
	public void setProductSkus(Product_Sku productSkus) {
		this.productSkus = productSkus;
	}



	/**
	 * @return the carts
	 */
	public Cart getCarts() {
		return carts;
	}



	/**
	 * @param carts the carts to set
	 */
	public void setCarts(Cart carts) {
		this.carts = carts;
	}



	@Override
	public String toString() {
		return "CartProduct [id=" + id + ", quantity=" + quantity + ", productSkus=" + productSkus + ", carts=" + carts
				+ "]";
	}

	

	
	
}
