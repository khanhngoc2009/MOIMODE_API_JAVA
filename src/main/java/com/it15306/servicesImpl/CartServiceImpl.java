package com.it15306.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.Cart;
import com.it15306.repository.CartRepository;
import com.it15306.services.CartService;

public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public List<Cart> getCartByUser(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getById(String cart_id, String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart saveUser(Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String cart_id) {
		// TODO Auto-generated method stub
		
	}

}
