package com.it15306.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.Province;
import com.it15306.repository.ProvinceRepository;
import com.it15306.services.ProvinceService;

public class ProvinceServiceImpl implements ProvinceService{
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Override
	public List<Province> getAllProvince() {
		// TODO Auto-generated method stub
		return provinceRepository.findAll();
	}

	@Override
	public Province getByIdProvince(String Province_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Province saveWard(Province province) {
		// TODO Auto-generated method stub
		return provinceRepository.save(province);
	}

	@Override
	public void delete(Integer province_id) {
		// TODO Auto-generated method stub
		provinceRepository.deleteById(province_id);
	}

}
