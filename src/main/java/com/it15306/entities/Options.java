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
@Table(name = "options")
@Getter
@Setter
@Component
public class Options {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private Integer option_id;
	
	@Basic
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date;
	
	private String name;
	private String description;
	private Integer status;
	
	
	@OneToMany(mappedBy = "option")
	private List<OptionValue> option_values = new ArrayList<>();
	
	@OneToMany(mappedBy = "option")
	private List<Option_Product> options_products = new ArrayList<>();
	
	
	public List<Option_Product> getOptions_products() {
		return options_products;
	}


	public void setOptions_products(List<Option_Product> options_products) {
		this.options_products = options_products;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Integer getOption_id() {
		return option_id;
	}


	public void setOption_id(Integer option_id) {
		this.option_id = option_id;
	}


	public Date getCreate_date() {
		return create_date;
	}


	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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



	public List<OptionValue> getOption_values() {
		return option_values;
	}


	public void setOption_values(List<OptionValue> option_values) {
		this.option_values = option_values;
	}


	
	
}