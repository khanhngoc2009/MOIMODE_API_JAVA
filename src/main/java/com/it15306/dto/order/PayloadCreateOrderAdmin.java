package com.it15306.dto.order;

import java.util.List;

import com.it15306.dto.cart.CartProductDTO;

public class PayloadCreateOrderAdmin {
	private Integer voucher_id;
	private Integer payment_id;
	private String note;
	private String phone;
	private String name;
	private List<ObjectCreateOrderDto> listProductSku;
	public Integer getVoucher_id() {
		return voucher_id;
	}
	public void setVoucher_id(Integer voucher_id) {
		this.voucher_id = voucher_id;
	}
	public Integer getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}
	
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ObjectCreateOrderDto> getListProductSku() {
		return listProductSku;
	}
	public void setListProductSku(List<ObjectCreateOrderDto> listProductSku) {
		this.listProductSku = listProductSku;
	}
	
	
}
