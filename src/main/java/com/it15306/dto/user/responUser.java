package com.it15306.dto.user;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;



public class responUser {
	private Integer id;
	@NotNull
	@Email
	private String email;
	@NotBlank
	private String username;
	private String password;
	@NotNull
	@NumberFormat
	private Integer admin;	
	private Integer activated;

	private String photo;
	@NotBlank
	@Length(min = 10,max = 15)
	private String phone;
	@NotBlank
	private String roles;
	@NotNull
	@NumberFormat
	private Integer province_id;
	@NotNull
	@NumberFormat
	private Integer district_id;
	@NotNull
	@NumberFormat
	private Integer ward_id;
	/**
	 * @return the id
	 */
	
	public Integer getId() {
		return id;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the admin
	 */
	public Integer getAdmin() {
		return admin;
	}
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
	/**
	 * @return the activated
	 */
	public Integer getActivated() {
		return activated;
	}
	/**
	 * @param activated the activated to set
	 */
	public void setActivated(Integer activated) {
		this.activated = activated;
	}
	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}
	/**
	 * @return the create_date
	 */

	/**
	 * @return the province_id
	 */
	public Integer getProvince_id() {
		return province_id;
	}
	/**
	 * @param province_id the province_id to set
	 */
	public void setProvince_id(Integer province_id) {
		this.province_id = province_id;
	}
	/**
	 * @return the district_id
	 */
	public Integer getDistrict_id() {
		return district_id;
	}
	/**
	 * @param district_id the district_id to set
	 */
	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}
	/**
	 * @return the ward_id
	 */
	public Integer getWard_id() {
		return ward_id;
	}
	/**
	 * @param ward_id the ward_id to set
	 */
	public void setWard_id(Integer ward_id) {
		this.ward_id = ward_id;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "responUser [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", admin=" + admin + ", activated=" + activated + ", photo=" + photo + ", phone=" + phone + ", roles="
				+ roles + ", province_id=" + province_id + ", district_id=" + district_id + ", ward_id=" + ward_id
				+ "]";
	}
	
	
	
}
