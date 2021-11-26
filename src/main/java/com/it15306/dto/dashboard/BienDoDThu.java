package com.it15306.dto.dashboard;

import java.util.List;

public class BienDoDThu {
	private List<String> thu;
	private List<Float> sumDoanhThu;
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
	 * @return the sumDoanhThu
	 */
	public List<Float> getSumDoanhThu() {
		return sumDoanhThu;
	}
	/**
	 * @param sumDoanhThu the sumDoanhThu to set
	 */
	public void setSumDoanhThu(List<Float> sumDoanhThu) {
		this.sumDoanhThu = sumDoanhThu;
	}
	
	
}
