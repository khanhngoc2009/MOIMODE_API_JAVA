package com.it15306.controller.client;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.AddressOrderDTO;
import com.it15306.dto.DistrictDTO;
import com.it15306.dto.PageDto;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.dto.WardDTO;
import com.it15306.dto.idBody;
import com.it15306.dto.addressOrder.BodyAddressOrder;
import com.it15306.entities.AddressOrder;
import com.it15306.entities.User;
import com.it15306.jwt.JwtTokenProvider;
import com.it15306.services.AddressService;
import com.it15306.services.UserService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerAddressOrder {
	
	@Autowired
	AddressService addressService;
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserService userservice;
	
	
//	@RequestMapping(value = "/address-order/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<List<AddressOrderDTO>> getListAddressOrder(@RequestBody PageDto data) {
//		List<AddressOrderDTO> list =addressService.getAllAddressOrder();
//		if(list.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
//		return ResponseEntity.ok(list);	 
//	}
	
	@RequestMapping(value = "/address-order/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AddressOrderDTO>> getListAddressOrderByUserID(HttpServletRequest httpServletRequest) {
		System.out.print("khoong laas dc ," + httpServletRequest.getHeader("Authorization"));
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		String username = tokenProvider.getUserNameFromJWT(token);
		User user = userservice.getByUsername(username);
		if(user!= null) {
			List<AddressOrderDTO> list =addressService.getAllAddressByUserId(String.valueOf(user.getId()));
			if(list.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(list);	 
		}else {
			return ResponseEntity.accepted().build();
		}
	}
	
	
	@RequestMapping(value = "/address-order/detail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AddressOrderDTO> detailAddressOrderByID(idBody data) {

		AddressOrderDTO dto= addressService.getAddressOrderById(data.getId());

		if(dto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);	
	}
	
	@RequestMapping(value = "/address-order/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<BodyAddressOrder> create(@Validated @RequestBody BodyAddressOrder addressOrderDTO, BindingResult bindingResult) {
		boolean check = bindingResult.hasErrors();
		if(!check) {
		if(addressOrderDTO.equals(null)) {
			System.out.println("vao vung loi null");
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(addressService.createAddressOrder(addressOrderDTO));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@RequestMapping(value = "/address-order/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> delete(@RequestBody idBody data) {
		Integer resullid = addressService.deleteAddressOrder(data.getId());
		if(resullid.equals(0)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok( resullid);
	}
	
	@RequestMapping(value = "/address-order/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<BodyAddressOrder> update(@Validated @RequestBody BodyAddressOrder addressOrderDTO, BindingResult bindingResult) {
		try {
			boolean check = bindingResult.hasErrors();
			if(addressOrderDTO.getId() == null)
				return ResponseEntity.badRequest().build();
			
			if(!check) {
			BodyAddressOrder addressOrder =	addressService.updateAddressOrder(addressOrderDTO);
			if(addressOrder != null) {
				return ResponseEntity.ok(addressOrder);
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
}
