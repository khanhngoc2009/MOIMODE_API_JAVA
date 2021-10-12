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
@Table(name = "Option_Value")
@Getter
@Setter
@Component
public class OptionValue {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
	private Integer value_id;
	
	
	private String value_name;
	
	@ManyToOne
	@JoinColumn(name = "option_id")
	private OptionProduct option = new OptionProduct();

	public Integer getValue_id() {
		return value_id;
	}

	public void setValue_id(Integer value_id) {
		this.value_id = value_id;
	}

	public String getValue_name() {
		return value_name;
	}

	public void setValue_name(String value_name) {
		this.value_name = value_name;
	}

	public OptionProduct getOption() {
		return option;
	}

	public void setOption(OptionProduct option) {
		this.option = option;
	}
	
	
}
