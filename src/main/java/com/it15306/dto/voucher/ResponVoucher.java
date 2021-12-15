package com.it15306.dto.voucher;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ResponVoucher {
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
	
	private Integer CountVoucherUsed;

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

	/**
	 * @return the create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return the start_time
	 */
	public Date getStart_time() {
		return start_time;
	}

	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	/**
	 * @return the end_time
	 */
	public Date getEnd_time() {
		return end_time;
	}

	/**
	 * @param end_time the end_time to set
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the discount
	 */
	public Integer getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	/**
	 * @return the type_discount
	 */
	public Integer getType_discount() {
		return type_discount;
	}

	/**
	 * @param type_discount the type_discount to set
	 */
	public void setType_discount(Integer type_discount) {
		this.type_discount = type_discount;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the countVoucherUsed
	 */
	public Integer getCountVoucherUsed() {
		return CountVoucherUsed;
	}

	/**
	 * @param countVoucherUsed the countVoucherUsed to set
	 */
	public void setCountVoucherUsed(Integer countVoucherUsed) {
		CountVoucherUsed = countVoucherUsed;
	}
	
	
	
	
	
}
