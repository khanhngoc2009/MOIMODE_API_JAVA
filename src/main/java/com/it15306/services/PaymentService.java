package com.it15306.services;

import java.util.List;

//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface PaymentService {
	List<User> getAllUsers();
	User getById(String id);
	User getByEmail(String id);
	User saveUser(User user);
	User getByUsername(String username);
	void delete(String id);
}
