package com.it15306.dto.order;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.it15306.dto.payment.PaymentDTO;
import com.it15306.dto.voucher.Voucherdto;

public class OrderDetailDto {
	private Integer id;
	private Date create_date;
	private Double total_price;
	private Integer status;
	private String note;
	private double transportationCost = 30000;
	private Integer isEvaluate = 0;
	
	public double getTransportationCost() {
		return transportationCost;
	}

	public void setTransportationCost(double transportationCost) {
		this.transportationCost = transportationCost;
	}

	public Integer getIsEvaluate() {
		return isEvaluate;
	}

	public void setIsEvaluate(Integer isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	private Integer type_payment;
	private List<ProductOrderDto> listProduct;
	private PaymentDTO payment;
	private Voucherdto vocuher;
	public Integer getId() {
		return id;
	}
	
	public Integer getType_payment() {
		return type_payment;
	}

	public void setType_payment(Integer type_payment) {
		this.type_payment = type_payment;
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
	public PaymentDTO getPayment() {
		return payment;
	}
	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}
	public Voucherdto getVocuher() {
		return vocuher;
	}
	public void setVocuher(Voucherdto vocuher) {
		this.vocuher = vocuher;
	}
	
}
