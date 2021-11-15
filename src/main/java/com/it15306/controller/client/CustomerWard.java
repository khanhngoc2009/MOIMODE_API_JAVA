package com.it15306.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.UserDTO;
import com.it15306.dto.WardDTO;
import com.it15306.entities.District;
import com.it15306.entities.User;
import com.it15306.entities.Ward;
import com.it15306.servicesImpl.WardServiceIpml;


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerWard {
	@Autowired
	private WardServiceIpml wardServiceImpl;
	


	@RequestMapping(value = "/ward/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WardDTO> getListWard() {
		ModelMapper modelMapper = new ModelMapper();
		List<Ward> Wards = this.wardServiceImpl.getAllWards();
		List<WardDTO> WardDTOs =new ArrayList<WardDTO>();
		if (Wards.size() > 0) {
			
			for (int i = 0; i < Wards.size(); i++) {
				WardDTOs.add(modelMapper.map(Wards.get(i), WardDTO.class));
			}
			return WardDTOs;
		}
		return WardDTOs;
	}
	@PreAuthorize("hasAuthority('CUSTOMER')")
	@RequestMapping(value = "/ward/{district_id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WardDTO> getListWardByDistrict(@PathVariable Integer district_id) {
		ModelMapper modelMapper = new ModelMapper();
		List<WardDTO> WardDTOs =new ArrayList<WardDTO>();
		District district = new District();
		district.setId(district_id);
		List<Ward> entitis =  this.wardServiceImpl.getAllWardByDistrict(district);
		if (entitis.size() > 0) {
			
			for (int i = 0; i < entitis.size(); i++) {
				WardDTOs.add(modelMapper.map(entitis.get(i), WardDTO.class));
			}
			return WardDTOs;
		}
		
		
		return WardDTOs;
	}
}
