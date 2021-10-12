package com.it15306.services;

import java.util.List;

import com.it15306.entities.Order;
//import com.it15306.entities.Category;

public interface OrderService {
	List<Order> getAllUsers();
	Order getById(String id);
	Order saveUser(Order Order);
	void delete(String id);
}
