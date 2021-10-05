package com.it15306.services;

import java.util.List;

import com.it15306.entities.Province;

public interface ProvinceService {
	List<Province> getAllProvince();

	Province getByIdProvince(String Province_id);

	Province saveWard(Province province);

	void delete(Integer province_id);
}
