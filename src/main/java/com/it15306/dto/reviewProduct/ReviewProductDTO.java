package com.it15306.dto.reviewProduct;

import java.util.Date;


import com.it15306.dto.UserDTO;
import com.it15306.dto.product.ProductDTO;

public class ReviewProductDTO{
	
	
	
	private Integer id;
	
	private Integer rating;
	
	private String comment;
	
	private Date createDate;
	
	private Integer status;	
	
	private  UserDTO userdto;	
	
	private  ProductDTO productsdto;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the rating
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the userdto
	 */
	public UserDTO getUserdto() {
		return userdto;
	}

	/**
	 * @param userdto the userdto to set
	 */
	public void setUserdto(UserDTO userdto) {
		this.userdto = userdto;
	}

	/**
	 * @return the productsdto
	 */
	public ProductDTO getProductsdto() {
		return productsdto;
	}

	/**
	 * @param productsdto the productsdto to set
	 */
	public void setProductsdto(ProductDTO productsdto) {
		this.productsdto = productsdto;
	}

	
	
	
}
