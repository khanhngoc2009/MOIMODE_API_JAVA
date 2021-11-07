package com.it15306.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.it15306.dto.cart.CartDTO;
import com.it15306.dto.cart.CartProductDTO;
import com.it15306.dto.cart.dataBodyCart;
import com.it15306.dto.cart.dataDeleteCart;
import com.it15306.entities.Cart;
import com.it15306.entities.CartProduct;
//import com.it15306.entities.Category;
@Service
public interface CartService {
	CartDTO getCartByUser(Integer user_id);
	
	Cart getById(String cart_id,String user_id);
	
	Cart saveUser(Cart cart);
	
	void delete(dataDeleteCart data);
	
	CartProductDTO insertProductToCart(dataBodyCart data); 
	
}
