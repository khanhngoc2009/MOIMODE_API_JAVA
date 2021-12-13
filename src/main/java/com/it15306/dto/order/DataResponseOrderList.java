package com.it15306.dto.order;

import java.util.List;

public class DataResponseOrderList <T> {
	private Integer code;
	private String message;
	private List<T> listData;
	private Integer count;
	private Integer countUnConfirmred;
	private Integer countConfirmred;
	private Integer countBeginTranforted;
	

	public Integer getCountUnConfirmred() {
		return countUnConfirmred;
	}
	public void setCountUnConfirmred(Integer countUnConfirmred) {
		this.countUnConfirmred = countUnConfirmred;
	}
	public Integer getCountConfirmred() {
		return countConfirmred;
	}
	public void setCountConfirmred(Integer countConfirmred) {
		this.countConfirmred = countConfirmred;
	}
	public Integer getCountBeginTranforted() {
		return countBeginTranforted;
	}
	public void setCountBeginTranforted(Integer countBeginTranforted) {
		this.countBeginTranforted = countBeginTranforted;
	}
	public List<T> getListData() {
		return listData;
	}
	public void setListData(List<T> listData) {
		this.listData = listData;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
