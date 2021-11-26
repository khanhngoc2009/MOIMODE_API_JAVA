package com.it15306.dto.dashboard;

import java.util.List;

public class BienDoDHang {
	private List<String> thu;
	private List<Integer> soLuongDHang;
	/**
	 * @return the thu
	 */
	public List<String> getThu() {
		return thu;
	}
	/**
	 * @param thu the thu to set
	 */
	public void setThu(List<String> thu) {
		this.thu = thu;
	}
	/**
	 * @return the soLuongDHang
	 */
	public List<Integer> getSoLuongDHang() {
		return soLuongDHang;
	}
	/**
	 * @param soLuongDHang the soLuongDHang to set
	 */
	public void setSoLuongDHang(List<Integer> soLuongDHang) {
		this.soLuongDHang = soLuongDHang;
	}
	
	
}
