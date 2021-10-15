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
@Table(name = "Warehouse")
@Getter
@Setter
@Component
public class Warehouse {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ware_house_id")
	private Integer ware_house_id;
	
	private String address_detail; 
	private Integer status;
	
	private String hotline; 
	private String user_name;
	private String facebook_url;
	
	
	@ManyToOne
	@JoinColumn(name = "province_id")
	private Province province = new Province();
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district = new District();
	
	@ManyToOne
	@JoinColumn(name = "ward_id")
	private Ward ward = new Ward();
	
	@OneToMany(mappedBy = "warehouse")
	private List<Product> products = new ArrayList<>();
}
