package com.it15306.dto;


import java.util.Date;



public class AddressOrderDTO {

	
	private Integer id;
	

	private Date create_date;
	
	
	private ProvinceDTO provincedto;
	
	private DistrictDTO districtdto;
	
	
	private WardDTO warddto;
	
	private UserDTO userdto;
	
	
	private String address_detail;
	private Integer isDefault;
	private String name_persion;
	private String phone_persion;
	private Integer isActive;
	private Integer status;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public ProvinceDTO getProvincedto() {
		return provincedto;
	}
	public void setProvincedto(ProvinceDTO provincedto) {
		this.provincedto = provincedto;
	}
	public DistrictDTO getDistrictdto() {
		return districtdto;
	}
	public void setDistrictdto(DistrictDTO districtdto) {
		this.districtdto = districtdto;
	}
	public WardDTO getWarddto() {
		return warddto;
	}
	public void setWarddto(WardDTO warddto) {
		this.warddto = warddto;
	}
	public UserDTO getUserdto() {
		return userdto;
	}
	public void setUserdto(UserDTO userdto) {
		this.userdto = userdto;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	


}
