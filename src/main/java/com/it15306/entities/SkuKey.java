package com.it15306.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SkuKey implements Serializable{
	
	
	@Column(name = "product_sku_id")
	Integer product_sku_id;
	
	@Column(name = "option_sku_id")
	Integer option_sku_id;
}
