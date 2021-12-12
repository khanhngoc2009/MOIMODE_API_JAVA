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

@Entity
@Table(name = "orders")
public class Order {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer order_id;
	@Basic
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date;
	private Double total_price;
	private Integer status;
	
	private String note;
	private Integer isEvaluate;
	
	
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
	
	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Payment payment = new Payment();
	public Integer getType_payment() {
		return type_payment;
	}
	public void setType_payment(Integer type_payment) {
		this.type_payment = type_payment;
	}
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user = new User();
	@ManyToOne
	@JoinColumn(name = "voucher_id")
	private Voucher voucher = new Voucher();
	
	@OneToMany(mappedBy = "order")
	private List<ProductOrder> product_orders = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "address_order_id")
	private AddressOrder address = new AddressOrder();
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Voucher getVoucher() {
		return voucher;
	}
	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}
	public AddressOrder getAddress() {
		return address;
	}
	public void setAddress(AddressOrder address) {
		this.address = address;
	}
	/**
	 * @return the total_price
	 */
	public Double getTotal_price() {
		return total_price;
	}
	/**
	 * @param total_price the total_price to set
	 */
	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	public List<ProductOrder> getProduct_orders() {
		return product_orders;
	}
	public void setProduct_orders(List<ProductOrder> product_orders) {
		this.product_orders = product_orders;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
