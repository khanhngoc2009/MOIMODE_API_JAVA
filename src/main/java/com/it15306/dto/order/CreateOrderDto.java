package com.it15306.dto.order;

import java.util.List;

public class CreateOrderDto {
	private Integer voucher_id;
	private Integer payment_id;
	private Integer address_id;
	private List<Integer> listCartId;
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
	public Integer getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public List<Integer> getListCartId() {
		return listCartId;
	}
	public void setListCartId(List<Integer> listCartId) {
		this.listCartId = listCartId;
	}
//	private Integer 
}