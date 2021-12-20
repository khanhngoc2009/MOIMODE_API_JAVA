package com.it15306.dto.order;

public class PayloadReviewOrder {
    private Integer id;	// day la cai gi khong biet
	private Integer rating;	// vote
	private String comment;		// 	
	private  Integer productId; // 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

    
}
