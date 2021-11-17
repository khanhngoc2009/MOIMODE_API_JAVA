package com.it15306.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MultiFileDto {
	
	private MultipartFile[] uploadedFiles;
	private Integer product_id;
	
	public MultipartFile[] getUploadedFiles() {
		return uploadedFiles;
	}

	public void setUploadedFiles(MultipartFile[] uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	
}
