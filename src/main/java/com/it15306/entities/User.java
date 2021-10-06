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
@Table(name = "user")
@Getter
@Setter
@Component
public class User {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	private String email;
	private String password;
	private String username;
	private Integer admin;
	private Integer activated;
	private String photo;
	private String phone;
	private String roles;
	
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
	
//	@ManyToOne
//	@JoinColumn(name = "username")
//	private Authority auth = new Authority();
	
	@ManyToOne
	@JoinColumn(name = "ward_id")
	private Ward ward = new Ward();
	
	

	@OneToMany(mappedBy = "user")
	private List<Cart> cart = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<AddressOrder> address_order = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<Order> order = new ArrayList<>();


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
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
	

//	public Authority getAuth() {
//		return auth;
//	}
//
//	public void setAuth(Authority auth) {
//		this.auth = auth;
//	}

	

	public List<Cart> getCart() {
		return cart;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public List<AddressOrder> getAddress_order() {
		return address_order;
	}

	public void setAddress_order(List<AddressOrder> address_order) {
		this.address_order = address_order;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public Integer getActivated() {
		return activated;
	}

	public void setActivated(Integer activated) {
		this.activated = activated;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
