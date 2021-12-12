package com.it15306.dto.order;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.it15306.dto.AddressOrderDTO;
import com.it15306.dto.payment.PaymentDTO;
import com.it15306.dto.voucher.Voucherdto;

public class OrderDto {
	private Integer id;
	private Date createDate;
	private Double totalPrice;
	private Integer status;
	private Integer paymentStatus;
	
	private List<ProductOrderDto> listProduct;
	private Voucherdto voucher;
	private AddressOrderDTO addressOrder;
	private PaymentDTO paymentType;
	private String note;
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<ProductOrderDto> getListProduct() {
		return listProduct;
	}
	public void setListProduct(List<ProductOrderDto> listProduct) {
		this.listProduct = listProduct;
	}
	public Voucherdto getVoucher() {
		return voucher;
	}
	public void setVoucher(Voucherdto voucher) {
		this.voucher = voucher;
	}
	public AddressOrderDTO getAddressOrder() {
		return addressOrder;
	}
	public void setAddressOrder(AddressOrderDTO addressOrder) {
		this.addressOrder = addressOrder;
	}
	public PaymentDTO getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentDTO paymentType) {
		this.paymentType = paymentType;
	}
	
	
}
