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
	private List<ProductOrderDto> listProduct;
	private PaymentDTO payment;
	private Voucherdto vocuher;
	
}
