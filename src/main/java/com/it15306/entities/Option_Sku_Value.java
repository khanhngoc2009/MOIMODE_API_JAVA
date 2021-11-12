package com.it15306.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "option_sku_value")
@Getter
@Setter
public class Option_Sku_Value {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_sku_id")
	private Integer option_sku_id;
	
	private Integer option_value_id;
	
	@OneToMany(mappedBy = "option_sku")
	private List<Sku> sku = new ArrayList<>();
	
	public List<Sku> getSku() {
		return sku;
	}
	public void setSku(List<Sku> sku) {
		this.sku = sku;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getOption_value_id() {
		return option_value_id;
	}
	public void setOption_value_id(Integer option_value_id) {
		this.option_value_id = option_value_id;
	}
	public Integer getOption_sku_id() {
		return option_sku_id;
	}
	public void setOption_sku_id(Integer option_sku_id) {
		this.option_sku_id = option_sku_id;
	}
	
	
}
