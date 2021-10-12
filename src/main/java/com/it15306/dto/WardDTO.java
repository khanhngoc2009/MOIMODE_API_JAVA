package com.it15306.dto;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class WardDTO {
	private Integer ward_id;
	
	private String ward_name; 
	private Integer status;
	private Integer district_id;

	public Integer getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}

	public Integer getWard_id() {
		return ward_id;
	}

	public void setWard_id(Integer ward_id) {
		this.ward_id = ward_id;
	}

	public String getWard_name() {
		return ward_name;
	}

	public void setWard_name(String ward_name) {
		this.ward_name = ward_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
