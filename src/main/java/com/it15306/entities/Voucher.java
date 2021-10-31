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
import lombok.ToString;

@Entity
@Table(name = "Voucher")
@Getter
@Setter
@ToString
public class Voucher {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "voucher_id")
	private Integer voucher_id;
	
	@Column(name = "code_voucher")
	private String codeVoucher;
	
	@Basic
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_time;
	
	@Basic
	@Column(name = "start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date start_time;
	
	@Basic
	@Column(name = "end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date end_time;
	
	
	
	private String title;
	private String description;
	private String url;
	private Integer discount;
	private Integer type_discount;
	private Integer status;
	
	@OneToMany(mappedBy = "voucher")
	private List<Order> cart = new ArrayList<>();

	public Integer getVoucher_id() {
		return voucher_id;
	}
	
	public void setVoucher_id(Integer voucher_id) {
		this.voucher_id = voucher_id;
	}
	
	public String getCodeVoucher() {
		return codeVoucher;
	}

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

	public List<Order> getCart() {
		return cart;
	}

	public void setCart(List<Order> cart) {
		this.cart = cart;
	}
	
	
}
