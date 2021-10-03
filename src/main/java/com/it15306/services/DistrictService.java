package com.it15306.services;

import java.util.List;

import com.it15306.entities.District;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface DistrictService {
	List<District> getAllDistricts();
	List<District> getAllDistrictsByProvinceId(String province_id);
	District getByDistrictId(String district_id);
	District saveDistrict(District district);
	void delete(String district_id);
}
