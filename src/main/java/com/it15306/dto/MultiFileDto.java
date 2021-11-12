package com.it15306.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MultiFileDto {
	private List<MultipartFile> uploadedFile;
	private Integer product_id;
	
	public List<MultipartFile> getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(List<MultipartFile> uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	
}
