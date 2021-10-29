package com.it15306.dto;


import java.util.Date;


public class Voucherdto {
	
	
	private Integer voucher_id;
	private String codeVoucher;
	private Date create_time;
	private Date start_time;
	private Date end_time;
	private String title;
	private String description;
	private String url;
	private Integer discount;
	private Integer type_discount;
	private Integer status;
	public Integer getVoucher_id() {
		return voucher_id;
	}
	public void setVoucher_id(Integer voucher_id) {
		this.voucher_id = voucher_id;
	}
	
	/**
	 * @return the codeVoucher
	 */
	public String getCodeVoucher() {
		return codeVoucher;
	}
	/**
	 * @param codeVoucher the codeVoucher to set
	 */
	public void setCodeVoucher(String codeVoucher) {
		this.codeVoucher = codeVoucher;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getType_discount() {
		return type_discount;
	}
	public void setType_discount(Integer type_discount) {
		this.type_discount = type_discount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	

}
