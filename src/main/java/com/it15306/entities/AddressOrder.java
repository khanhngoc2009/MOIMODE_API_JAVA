package com.it15306.entities;

import java.io.Serializable;
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
@Table(name = "Addressorder")
public class AddressOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_order_id")
	private Integer address_order_id;
	


	@Basic
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date;
	
	
	@ManyToOne
	@JoinColumn(name = "province_id")
	private Province province = new Province();
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district = new District();
	
	@ManyToOne
	@JoinColumn(name = "ward_id")
	private Ward ward = new Ward();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user = new User();
	
	
	private String address_detail;
	private Integer isdefault;
	private String name_persion;
	private String phone_persion;
	private Integer isactive;
	private Integer status;
	public Integer getAddress_order_id() {
		return address_order_id;
	}
	public void setAddress_order_id(Integer address_order_id) {
		this.address_order_id = address_order_id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public Ward getWard() {
		return ward;
	}
	public void setWard(Ward ward) {
		this.ward = ward;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public Integer getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}
	public String getName_persion() {
		return name_persion;
	}
	public void setName_persion(String name_persion) {
		this.name_persion = name_persion;
	}
	public String getPhone_persion() {
		return phone_persion;
	}
	public void setPhone_persion(String phone_persion) {
		this.phone_persion = phone_persion;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
