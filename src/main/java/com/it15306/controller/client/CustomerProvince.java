package com.it15306.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.entities.Province;
import com.it15306.entities.User;
import com.it15306.servicesImpl.ProvinceServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerProvince {
	@Autowired
	private ProvinceServiceImpl provinceServiceImpl;

	@RequestMapping(value = "/getListProvinces", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProvinceDTO> getListProvinces() {
		ModelMapper modelMapper = new ModelMapper();
		List<Province> provinces = this.provinceServiceImpl.getAllProvince();
		List<ProvinceDTO> provinceDTOs =new ArrayList<ProvinceDTO>();
		if (provinces.size() > 0) {
			
			for (int i = 0; i < provinces.size(); i++) {
				provinceDTOs.add(modelMapper.map(provinces.get(i), ProvinceDTO.class));
			}
			return provinceDTOs;
		}
		return provinceDTOs;
	}
}
