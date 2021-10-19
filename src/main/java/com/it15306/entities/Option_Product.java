package com.it15306.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "options_products")
@Getter
@Setter
public class Option_Product {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	OptionProductKey id;
	
	@ManyToOne
	@MapsId("option_id")
	@JoinColumn(name = "option_id")
	private Options option = new Options();
	
	@ManyToOne
	@MapsId("product_id")
	@JoinColumn(name = "product_id")
	private Product product = new Product();

	public Options getOption() {
		return option;
	}

	public void setOption(Options option) {
		this.option = option;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
