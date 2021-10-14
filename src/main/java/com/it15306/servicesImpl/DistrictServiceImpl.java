package com.it15306.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.entities.Category;
import com.it15306.entities.District;
import com.it15306.entities.Product;
import com.it15306.entities.Province;
import com.it15306.repository.CategoryRepository;
import com.it15306.repository.DistrictRepository;
import com.it15306.repository.ProductSkuValuesRepository;
import com.it15306.services.CategoryService;
import com.it15306.services.DistrictService;

@Service("DistrictServiceImpl")
public class DistrictServiceImpl implements DistrictService{
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public List<District> getAllDistricts() {
		// TODO Auto-generated method stub
		return  districtRepository.findAll();
	}

	@Override
	public List<District> getAllDistrictsByProvinceId(Province province) {
		// TODO Auto-generated method stub
		return districtRepository.findByProvince(province);
	}

	@Override
	public Optional<District> getByDistrictId(Integer district_id) {
		// TODO Auto-generated method stub
		return districtRepository.findById(district_id);
	}

	@Override
	public District saveDistrict(District district) {
		// TODO Auto-generated method stub
		return districtRepository.save(district);
	}

	@Override
	public void delete(Integer district_id) {
		// TODO Auto-generated method stub
		districtRepository.deleteById(district_id);
	}
	

}
