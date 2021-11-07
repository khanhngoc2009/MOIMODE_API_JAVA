package com.it15306.controller.client;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.it15306.dto.AddressOrderDTO;
import com.it15306.dto.DistrictDTO;
import com.it15306.dto.PageDto;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.dto.WardDTO;
import com.it15306.entities.AddressOrder;
import com.it15306.services.AddressService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerAddressOrder {
	
	@Autowired
	AddressService addressService;
	
	
	@RequestMapping(value = "/address-order/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AddressOrderDTO>> getListAddressOrder(@RequestBody PageDto data) {
		List<AddressOrderDTO> list =addressService.getAllAddressOrder();
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);	 
	}
	
	@RequestMapping(value = "/address-order/list/{user_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AddressOrderDTO>> getListAddressOrderByUserID(@PathVariable("user_id")String user_id) {
		List<AddressOrderDTO> list =addressService.getAllAddressByUserId(user_id);	
		
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);	 
	}
	
	
	@RequestMapping(value = "/address-order/detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AddressOrderDTO> detailAddressOrderByID(@PathVariable("id") Integer id) {

		AddressOrderDTO dto= addressService.getAddressOrderById(id);

		if(dto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dto);	
	}
	
	@RequestMapping(value = "/address-order/create/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AddressOrderDTO> create(@RequestBody AddressOrderDTO addressOrderDTO) {
		
		if(addressOrderDTO.equals(null)) {
			System.out.println("vao vung loi null");
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(addressService.createAddressOrder(addressOrderDTO));
	}
	
	@RequestMapping(value = "/address-order/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> delete(@PathVariable("id") Integer id) {
		Integer resullid = addressService.deleteAddressOrder(id);
		if(resullid.equals(0)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok( resullid);
	}
	
	@RequestMapping(value = "/address-order/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AddressOrder update(@RequestBody AddressOrderDTO addressOrderDTO) {
		
		return addressService.updateAddressOrder(addressOrderDTO);
	}
	
}
