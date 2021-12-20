package com.it15306.dto.order;

import java.util.List;

public class PayloadReviewOrderDto {

	private  List<PayloadReviewOrder> listReview;
	private Integer orderId;
	

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public List<PayloadReviewOrder> getListReview() {
		return listReview;
	}
	public void setListReview(List<PayloadReviewOrder> listReview) {
		this.listReview = listReview;
	}
	
	
}
