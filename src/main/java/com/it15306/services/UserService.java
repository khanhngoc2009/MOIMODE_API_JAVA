package com.it15306.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.it15306.dto.UserDTO;
import com.it15306.dto.user.customerUserBody;
import com.it15306.dto.user.dataBodyUser;
import com.it15306.dto.user.datatupdateUser;
import com.it15306.dto.user.responUser;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface UserService {
	List<User> getAllUsers();
	User getById(String id);
	User getByEmail(String id);
	User saveUser(User user);
	User getByUsername(String username);
	void delete(String id);
	List<UserDTO> getAllUsersv2(dataBodyUser data);
	Long totalement();
	UserDTO createUser(responUser data);
	UserDTO updateUser(datatupdateUser data);
	
	UserDTO updateUserCustomer(customerUserBody data, HttpServletRequest req);
}
