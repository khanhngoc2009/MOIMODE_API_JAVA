package com.it15306.services;

import java.util.List;

import com.it15306.entities.District;
import com.it15306.entities.Ward;

public interface WardService {
	List<Ward> getAllWards();
	
	List<Ward> getAllWardByDistrict(District district);
	
	Ward getByIdWard(String ward_id);

	Ward saveWard(Ward ward);

	void delete(Integer ward_id);
}
