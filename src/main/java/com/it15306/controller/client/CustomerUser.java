package com.it15306.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.UserDTO;
import com.it15306.dto.user.customerUserBody;
import com.it15306.mapper.UserMapper;
import com.it15306.services.UserService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerUser {
	
	@Autowired
	private UserService userService;
	

	
	@Autowired
	private UserMapper mapper;
	
	@PostMapping("user/update")
	public ResponseEntity<UserDTO> updateuserCustomer(@Validated  @RequestBody customerUserBody data, BindingResult bindingResult, HttpServletRequest req){
		
		try {
			boolean check = bindingResult.hasErrors();
			if(!check) {
				UserDTO dto =	userService.updateUserCustomer(data, req);
				if(dto != null)
					return ResponseEntity.ok(dto);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return ResponseEntity.badRequest().build();
	}
}
