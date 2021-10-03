package com.it15306.services;

import java.util.List;

import com.it15306.entities.Cart;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface CartService {
	List<Cart> getCartByUser(String user_id);
	
	Cart getById(String cart_id,String user_id);
	
	Cart saveUser(Cart cart);
	
	void delete(String cart_id);
}
