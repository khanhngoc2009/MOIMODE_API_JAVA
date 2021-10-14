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
@Table(name = "Province")
@Getter
@Setter
@Component
public class Province {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "province_id")
	private Integer province_id;
	
//	@Basic
//	@Column(name = "create_date")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date create_date;
	private String province_name; 
	private Integer status;
	
	@OneToMany(mappedBy = "province")
	private List<District> districts = new ArrayList<>();
	
	@OneToMany(mappedBy = "province")
	private List<User> users = new ArrayList<>();

	public Integer getProvince_id() {
		return province_id;
	}
	public Province() {
		
	}
	
	public Province(Integer province_id, String province_name, Integer status) {
		super();
		this.province_id = province_id;
		this.province_name = province_name;
		this.status = status;
	}

	public void setProvince_id(Integer province_id) {
		this.province_id = province_id;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
}
