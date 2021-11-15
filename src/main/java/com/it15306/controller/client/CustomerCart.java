package com.it15306.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.cart.CartDTO;
import com.it15306.dto.cart.CartProductDTO;
import com.it15306.dto.cart.dataBodyCart;
import com.it15306.dto.cart.dataDeleteCart;
import com.it15306.entities.CartProduct;
import com.it15306.services.CartService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerCart {
	@Autowired
	CartService cartService;
	
	@RequestMapping(value = "/cart/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CartProductDTO> Create(@RequestBody dataBodyCart data) {
		try {			
			CartProductDTO  dto =	cartService.insertProductToCart(data);
			if(dto != null) {
				return ResponseEntity.ok(dto);
			}
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	@RequestMapping(value = "/cart/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CartProductDTO> updateQuantity(@RequestBody dataBodyCart data) {
		try {			
			CartProductDTO  dto =	cartService.updateProductToCart(data);
			if(dto != null) {
				return ResponseEntity.ok(dto);
			}
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@RequestMapping(value = "/cart/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void delete(@RequestBody dataDeleteCart data) {
		
		cartService.delete(data);
		
	}
	
	@RequestMapping(value = "/cart/list/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CartDTO> getlistByUserId(@PathVariable("id") Integer id) {
		try {			
		CartDTO cart= cartService.getCartByUser(id);
		if(cart != null) {
			return ResponseEntity.ok(cart);
		}
		return  ResponseEntity.noContent().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}


}
