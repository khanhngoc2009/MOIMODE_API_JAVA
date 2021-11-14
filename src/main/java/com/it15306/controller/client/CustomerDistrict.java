package com.it15306.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.DistrictDTO;
import com.it15306.dto.UserDTO;
import com.it15306.entities.District;
import com.it15306.entities.Province;
import com.it15306.entities.District;
import com.it15306.entities.User;
import com.it15306.servicesImpl.DistrictServiceImpl;
import com.it15306.servicesImpl.DistrictServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerDistrict {
	@Autowired
	private DistrictServiceImpl districtServiceImpl;

	@RequestMapping(value = "/district/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DistrictDTO> getListDistrict() {
		ModelMapper modelMapper = new ModelMapper();
		List<District> Districts = this.districtServiceImpl.getAllDistricts();
		List<DistrictDTO> DistrictDTOs =new ArrayList<DistrictDTO>();
		if (Districts.size() > 0) {
			
			for (int i = 0; i < Districts.size(); i++) {
				DistrictDTOs.add(modelMapper.map(Districts.get(i), DistrictDTO.class));
			}
			return DistrictDTOs;
		}
		return DistrictDTOs;
	}
	@RequestMapping(value = "/district/{province_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<DistrictDTO> getListDistrictByProvinceId(@PathVariable Integer province_id) {
		ModelMapper modelMapper = new ModelMapper();
		List<DistrictDTO> DistrictDTOs =new ArrayList<DistrictDTO>();
		Province province = new Province();
		province.setId(province_id);
		List<District> entitis =  this.districtServiceImpl.getAllDistrictsByProvinceId(province);
				if (entitis.size() > 0) {
			
			for (int i = 0; i < entitis.size(); i++) {
				DistrictDTOs.add(modelMapper.map(entitis.get(i), DistrictDTO.class));
			}
			return DistrictDTOs;
		}
		return DistrictDTOs;
	}
}
