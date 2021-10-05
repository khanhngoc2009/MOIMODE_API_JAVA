package com.it15306.services;

import java.util.List;
import java.util.Optional;

import com.it15306.entities.District;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface DistrictService {
	List<District> getAllDistricts();
	List<District> getAllDistrictsByProvinceId(String province_id);
Optional<District> getByDistrictId(Integer district_id);
	District saveDistrict(District district);
	void delete(Integer district_id);
}
