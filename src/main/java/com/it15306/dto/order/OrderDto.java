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
	private Integer order_id;
	private Date create_date;
	private Double total_price;
	private Integer status;
	private Integer type_payment;
	public Integer getType_payment() {
		return type_payment;
	}
	public void setType_payment(Integer type_payment) {
		this.type_payment = type_payment;
	}
	private List<ProductOrderDto> listProduct;
	private Voucherdto voucher;
	private AddressOrderDTO addressOrder;
	private PaymentDTO paymentType;
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
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
