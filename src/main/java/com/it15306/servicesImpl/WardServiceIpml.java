package com.it15306.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.entities.District;
import com.it15306.entities.Ward;
import com.it15306.repository.WardRepository;
import com.it15306.services.WardService;


@Service("WardServiceIpml")
public class WardServiceIpml implements WardService{
	
	@Autowired
	private WardRepository wardRepository;
	
	@Override
	public List<Ward> getAllWards() {
		// TODO Auto-generated method stub
		return wardRepository.findAll();
	}

	@Override
	public List<Ward> getAllWardByDistrict(District district) {
		// TODO Auto-generated method stub
		return wardRepository.findByDistrict(district);
	}

	@Override
	public Ward getByIdWard(String ward_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ward saveWard(Ward ward) {
		// TODO Auto-generated method stub
		return wardRepository.save(ward);
	}

	@Override
	public void delete(Integer ward_id) {
		// TODO Auto-generated method stub
		wardRepository.deleteById(ward_id);
	}

}
