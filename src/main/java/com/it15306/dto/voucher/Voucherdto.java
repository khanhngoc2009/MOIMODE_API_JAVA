package com.it15306.dto.voucher;


import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


public class Voucherdto {
	
	@NotNull
	private Integer id;
	@NotBlank
	private String codeVoucher;

	private Date create_time;
	@NotNull
	private Date start_time;
	@NotNull
	private Date end_time;
	@NotBlank
	private String title;
	private String description;
	private String url;
	@NotNull
	private Integer discount;
	@NotNull
	private Integer type_discount;
	@NotNull
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
