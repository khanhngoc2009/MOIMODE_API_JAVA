package com.it15306.dto.category;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.it15306.entities.Category;



public class CategoryDTO {

	private Integer id;
	
	private Date create_date;
	@NotNull
	private Integer status;
	private Integer category_parent_id;
	@NotNull
	private Integer type;
	@NotBlank
	private String name;
	private String description;
	
	private String image;
	private CategoryDTO categoryParentDTO;

	

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(Integer id, Date create_date, Integer status, String category_name, String image) {
		super();
		this.id = id;
		this.create_date = create_date;
		this.status = status;
		this.name = category_name;
		this.image = image;
	}

	public Integer getId() {
		return id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCategory_parent_id() {
		return category_parent_id;
	}

	public void setCategory_parent_id(Integer category_parent_id) {
		this.category_parent_id = category_parent_id;
	}

	public Integer getType() {
		return type;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the categoryParentDTO
	 */
	public CategoryDTO getCategoryParentDTO() {
		return categoryParentDTO;
	}

	/**
	 * @param categoryParentDTO the categoryParentDTO to set
	 */
	public void setCategoryParentDTO(CategoryDTO categoryParentDTO) {
		this.categoryParentDTO = categoryParentDTO;
	}

	



}
