package com.it15306.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.Order;
import com.it15306.repository.OrderRepository;
import com.it15306.services.OrderService;

public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order saveUser(Order Order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
