package com.it15306.dto;


public class WarehouseDTO {
	
	private Integer ware_house_id;
	
	private String address_detail; 
	private Integer status;
	
	private String hotline; 
	private String user_name;
	private String facebook_url;
	
	
	private ProvinceDTO provincedto;
	private DistrictDTO districtdto;
	
	private WardDTO warddto;

	public Integer getWare_house_id() {
		return ware_house_id;
	}

	public void setWare_house_id(Integer ware_house_id) {
		this.ware_house_id = ware_house_id;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHotline() {
		return hotline;
	}

	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFacebook_url() {
		return facebook_url;
	}

	public void setFacebook_url(String facebook_url) {
		this.facebook_url = facebook_url;
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

	@Override
	public String toString() {
		return "WarehouseDTO [ware_house_id=" + ware_house_id + ", address_detail=" + address_detail + ", status="
				+ status + ", hotline=" + hotline + ", user_name=" + user_name + ", facebook_url=" + facebook_url
				+ ", provincedto=" + provincedto + ", districtdto=" + districtdto + ", warddto=" + warddto + "]";
	}
	
	
}
