package com.it15306.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sku")
@Getter
@Setter
public class Sku {
	@EmbeddedId
	SkuKey id;
	
	@ManyToOne
	@MapsId("product_sku_id")
	@JoinColumn(name = "product_sku_id")
	private Product_Sku product_sku = new Product_Sku();
	
	@ManyToOne
	@MapsId("option_sku_id")
	@JoinColumn(name = "option_sku_id")
	private Option_Sku_Value option_sku = new Option_Sku_Value();

	public Product_Sku getProduct_sku() {
		return product_sku;
	}

	public void setProduct_sku(Product_Sku product_sku) {
		this.product_sku = product_sku;
	}

	public Option_Sku_Value getOption_sku() {
		return option_sku;
	}

	public void setOption_sku(Option_Sku_Value option_sku) {
		this.option_sku = option_sku;
	}
	
}
