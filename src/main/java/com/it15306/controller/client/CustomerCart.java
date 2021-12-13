package com.it15306.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.it15306.entities.User;
import com.it15306.jwt.JwtTokenProvider;
import com.it15306.services.CartService;
import com.it15306.services.UserService;
import com.it15306.utils.Const;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200",Const.urlServer })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerCart {
	@Autowired
	CartService cartService;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserService userservice;
	
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
	
	@RequestMapping(value = "/cart/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CartDTO> getlistByUserId(HttpServletRequest httpServletRequest) {
		
		try {		
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		String username = tokenProvider.getUserNameFromJWT(token);
		User user = userservice.getByUsername(username);
		if( user != null) {
			CartDTO cart= cartService.getCartByUser(user.getId());
			if(cart != null) {
				return ResponseEntity.ok(cart);
			}
			return  ResponseEntity.noContent().build();
			
		}else {
			return  ResponseEntity.noContent().build();
		}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}


}
