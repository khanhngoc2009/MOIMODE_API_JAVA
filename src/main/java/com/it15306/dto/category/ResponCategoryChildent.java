package com.it15306.dto.category;

import java.util.Date;

public class ResponCategoryChildent {
	private Integer id;
	
	private Date create_date;
	private Integer category_parent_id;
	private Integer status;	
	private Integer numberOfProduct;	
	private Integer type;
	private String name;
	private String description;
	private String image;
	private categoryParent categoryParent;
	
	
	/**
	 * @return the numberOfProduct
	 */
	public Integer getNumberOfProduct() {
		return numberOfProduct;
	}
	/**
	 * @param numberOfProduct the numberOfProduct to set
	 */
	public void setNumberOfProduct(Integer numberOfProduct) {
		this.numberOfProduct = numberOfProduct;
	}
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
	 * @return the create_date
	 */
	public Date getCreate_date() {
		return create_date;
	}
	/**
	 * @param create_date the create_date to set
	 */
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	/**
	 * @return the category_parent_id
	 */
	public Integer getCategory_parent_id() {
		return category_parent_id;
	}
	/**
	 * @param category_parent_id the category_parent_id to set
	 */
	public void setCategory_parent_id(Integer category_parent_id) {
		this.category_parent_id = category_parent_id;
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
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the categoryParent
	 */
	public categoryParent getCategoryParent() {
		return categoryParent;
	}
	/**
	 * @param categoryParent the categoryParent to set
	 */
	public void setCategoryParent(categoryParent categoryParent) {
		this.categoryParent = categoryParent;
	}

		
	
}
