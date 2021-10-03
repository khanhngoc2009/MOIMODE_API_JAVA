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
@Table(name = "Voucher")
@Getter
@Setter
@Component
public class Voucher {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "voucher_id")
	private Integer voucher_id;
	@Basic
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date;
	
	@Basic
	@Column(name = "start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date start_time;
	
	@Basic
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date end_date;
	
	private String title;
	private String description;
	private String url;
	private Integer discount;
	private Integer type_discount;
	private Integer status;
	
	@OneToMany(mappedBy = "voucher")
	private List<Order> cart = new ArrayList<>();
	
//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}
	
}
