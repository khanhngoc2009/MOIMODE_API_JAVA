package com.it15306.controller.client;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponse;
import com.it15306.dto.order.CreateOrderDto;
import com.it15306.entities.Order;
import com.it15306.servicesImpl.OrderServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerOrder {
	
	@Autowired
	ModelMapper modelMapper;
	@RequestMapping(value = "/order/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto dto) {
		DataResponse<String> data = new DataResponse<String>(); 
		try {
			
			
			data.setCode(200);
			data.setMessage("Success");
			return new ResponseEntity<>(data,HttpStatus.OK);
		} catch (Exception e) {
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
	
	
}
