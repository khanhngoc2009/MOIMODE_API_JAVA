package com.it15306.dto.dashboard;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

public class YearBody {
	@NumberFormat
	@NotNull
	private Integer year;

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	
	
}
