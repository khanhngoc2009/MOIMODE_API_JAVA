package com.it15306.dto.dashboard;

import java.util.List;

public class DataResponseListTKe<T>{
	private Integer code;
	private String message;
	private List<T> listData;
	private Float tong;
	private Integer count;
	
	
	/**
	 * @return the countTotalElement
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * @param countTotalElement the countTotalElement to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the listData
	 */
	public List<T> getListData() {
		return listData;
	}
	/**
	 * @param listData the listData to set
	 */
	public void setListData(List<T> listData) {
		this.listData = listData;
	}
	/**
	 * @return the tong
	 */
	public Float getTong() {
		return tong;
	}
	/**
	 * @param tong the tong to set
	 */
	public void setTong(Float tong) {
		this.tong = tong;
	}
	
	
}
