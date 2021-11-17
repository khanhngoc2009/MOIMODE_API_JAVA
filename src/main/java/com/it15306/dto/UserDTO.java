package com.it15306.dto;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.stereotype.Component;

import com.it15306.entities.District;
import com.it15306.entities.Province;
import com.it15306.entities.Ward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Component
public class UserDTO {
private Integer id;
	
	private String email;
	private String password;
	private String username;
	private Integer admin;
	private Integer activated;
	private String photo;
	private String roles;
	private Date create_date;
	private ProvinceDTO provincedto;
	private DistrictDTO districtdto;
	private WardDTO warddto;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
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
	public Date getCreate_date() {
		return create_date;
	}
	/**
	 * @param create_date the create_date to set
	 */
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	/**
	 * @return the provincedto
	 */
	public ProvinceDTO getProvincedto() {
		return provincedto;
	}
	/**
	 * @param provincedto the provincedto to set
	 */
	public void setProvincedto(ProvinceDTO provincedto) {
		this.provincedto = provincedto;
	}
	/**
	 * @return the districtdto
	 */
	public DistrictDTO getDistrictdto() {
		return districtdto;
	}
	/**
	 * @param districtdto the districtdto to set
	 */
	public void setDistrictdto(DistrictDTO districtdto) {
		this.districtdto = districtdto;
	}
	/**
	 * @return the warddto
	 */
	public WardDTO getWarddto() {
		return warddto;
	}
	/**
	 * @param warddto the warddto to set
	 */
	public void setWarddto(WardDTO warddto) {
		this.warddto = warddto;
	}
	
	
	
	
}
