package com.it15306.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Columns;


@Embeddable
public class CartProductPK implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name="cart_id", insertable=false, updatable=false)
	private Integer cartId;

	@Column(name="product_sku_id", insertable=false, updatable=false)
	private Integer productSkuId;
}
