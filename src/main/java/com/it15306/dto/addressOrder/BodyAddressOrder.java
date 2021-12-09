package com.it15306.dto.addressOrder;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class BodyAddressOrder {
	private Integer id;
	@NotBlank
	private String name_persion;
	@NotBlank
	@Length(max = 15, min = 10)
	private String phone_persion;
	private String address_detail;
	private Integer isActive;
	@NotNull
	private Integer isDefault;
	@NotNull
	private Integer district_id;
	@NotNull
	private Integer province_id;
	@NotNull
	private Integer ward_id;
	@NotNull
	private Integer user_id;
	
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
	 * @return the name_persion
	 */
	public String getName_persion() {
		return name_persion;
	}
	/**
	 * @param name_persion the name_persion to set
	 */
	public void setName_persion(String name_persion) {
		this.name_persion = name_persion;
	}
	/**
	 * @return the phone_persion
	 */
	public String getPhone_persion() {
		return phone_persion;
	}
	/**
	 * @param phone_persion the phone_persion to set
	 */
	public void setPhone_persion(String phone_persion) {
		this.phone_persion = phone_persion;
	}
	/**
	 * @return the address_detail
	 */
	public String getAddress_detail() {
		return address_detail;
	}
	/**
	 * @param address_detail the address_detail to set
	 */
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	/**
	 * @return the isActive
	 */
	public Integer getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the isDefault
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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
	 * @return the user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	
	
	
}
