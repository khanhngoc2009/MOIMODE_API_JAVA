package com.it15306.services;

import java.util.List;

//import com.it15306.entities.Category;
import com.it15306.entities.User;
import com.it15306.entities.Ward;

public interface WardService {
	List<Ward> getAllWards();
	Ward getByIdWard(String ward_id);

	Ward saveWard(Ward ward);

	void delete(String ward_id);
}
