package com.it15306.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.dto.AddressOrderDTO;
import com.it15306.dto.DistrictDTO;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.dto.WardDTO;
import com.it15306.entities.AddressOrder;
import com.it15306.entities.District;
import com.it15306.entities.Province;
import com.it15306.entities.User;
import com.it15306.entities.Ward;
import com.it15306.repository.AddressOrderRepository;
import com.it15306.repository.UserRepository;
import com.it15306.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressOrderRepository addressOrderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<AddressOrderDTO> getAllAddressOrder() {
		List<AddressOrderDTO> mode = new ArrayList<AddressOrderDTO>();
		List<AddressOrder> enti = addressOrderRepository.findAll();

		if (enti.size() > 0) {

			for (int i = 0; i < enti.size(); i++) {
				AddressOrderDTO vo = modelMapper.map(enti.get(i), AddressOrderDTO.class);
					vo.setProvincedto(modelMapper.map(enti.get(i).getProvince(), ProvinceDTO.class));
					vo.setDistrictdto(modelMapper.map(enti.get(i).getDistrict(), DistrictDTO.class));
					vo.setWarddto(modelMapper.map(enti.get(i).getWard(), WardDTO.class));
				mode.add(vo);
				
			}
			return mode;
		}

		return mode;
	}

	@Override
	public List<AddressOrderDTO> getAllAddressByUserId(String user_id) {

		List<AddressOrderDTO> mode = new ArrayList<AddressOrderDTO>();
		List<AddressOrder> enti = addressOrderRepository.findAddressOrderByUserID(user_id);

		if (enti.size() > 0) {

			for (int i = 0; i < enti.size(); i++) {
				AddressOrderDTO vo =modelMapper.map(enti.get(i), AddressOrderDTO.class);
				vo.setProvincedto(modelMapper.map(enti.get(i).getProvince(), ProvinceDTO.class));
				vo.setDistrictdto(modelMapper.map(enti.get(i).getDistrict(), DistrictDTO.class));
				vo.setWarddto(modelMapper.map(enti.get(i).getWard(), WardDTO.class));
				mode.add(vo);
			}
			return mode;
		}
		return mode;
	}

	@Override
	public AddressOrderDTO getAddressOrderById(Integer address_order_id) {
		AddressOrderDTO mode = new AddressOrderDTO();
		AddressOrder enti = addressOrderRepository.findAddressOrderByOrderAddressId(address_order_id);

		if (enti != null) {

			mode = modelMapper.map(enti, AddressOrderDTO.class);
			mode.setProvincedto(modelMapper.map(enti.getProvince(), ProvinceDTO.class));
			mode.setDistrictdto(modelMapper.map(enti.getDistrict(), DistrictDTO.class));
			mode.setWarddto(modelMapper.map(enti.getWard(), WardDTO.class));
			return  mode;
		}
		return null;
	}

	@Override
	public AddressOrderDTO createAddressOrder(AddressOrderDTO addressOrderDTO) {
		System.out.println("---" + addressOrderDTO.getAddress_detail());
		Date datenow = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");

		AddressOrder mode = new AddressOrder();
		UserDTO userdto = new UserDTO();
		User user = new User();
		ProvinceDTO provincedto = new ProvinceDTO();
		Province province = new Province();
		DistrictDTO districtDTO = new DistrictDTO();
		District district = new District();
		WardDTO wardDTO = new WardDTO();
		Ward ward = new Ward();

		user = modelMapper.map(addressOrderDTO.getUserdto(), User.class);
		province = modelMapper.map(addressOrderDTO.getProvincedto(), Province.class);
		district = modelMapper.map(addressOrderDTO.getDistrictdto(), District.class);
		ward = modelMapper.map(addressOrderDTO.getWarddto(), Ward.class);
		mode = modelMapper.map(addressOrderDTO, AddressOrder.class);

		mode.setDistrict(district);
		mode.setProvince(province);

		mode.setWard(ward);
		mode.setUser(user);
		addressOrderRepository.save(mode);
		addressOrderDTO.setAddress_order_id(mode.getAddress_order_id());
		System.out.println("idd"+mode.getAddress_order_id());
		return addressOrderDTO;

	}

	@Override
	public Integer deleteAddressOrder(Integer address_order_id) {
		Optional<AddressOrder> optional  = addressOrderRepository.findById(address_order_id);
		if (optional .isPresent()) {
			System.out.println("vooooooooooo");
			AddressOrder addressOrder=optional .get();
			addressOrderRepository.delete(addressOrder);
			
			return addressOrder.getAddress_order_id();
		}
		System.out.println("rooooooooooo");
		return 0;
	}

	@Override
	public AddressOrder updateAddressOrder(AddressOrderDTO addressOrderDTO) {
		try {
		Optional<AddressOrder> optional=addressOrderRepository.findById(addressOrderDTO.getAddress_order_id());
		if(optional.isPresent()) {
			System.out.println("vooooooooooooo");
			AddressOrder addressOrder=optional.get();
			System.out.println(addressOrderDTO.toString());
			//BeanUtils.copyProperties(addressOrderDTO, addressOrder);
			AddressOrder addressOrdernew =	convertToEntity(addressOrderDTO, addressOrder);
			addressOrderRepository.save(addressOrdernew);
			
			return addressOrdernew ;
		}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("rooooooooooooo");
		return null;
	}
	
	
	
	
	public AddressOrder convertToEntity(AddressOrderDTO model, AddressOrder entity) throws IllegalAccessException, InvocationTargetException{
//		if(entity == null)
//			entity = new AddressOrder();
		

		UserDTO userdto = new UserDTO();
		User user = new User();
		ProvinceDTO provincedto = new ProvinceDTO();
		Province province = new Province();
		DistrictDTO districtDTO = new DistrictDTO();
		District district = new District();
		WardDTO wardDTO = new WardDTO();
		Ward ward = new Ward();

		user = modelMapper.map(model.getUserdto(), User.class);
		province = modelMapper.map(model.getProvincedto(), Province.class);
		district = modelMapper.map(model.getDistrictdto(), District.class);
		ward = modelMapper.map(model.getWarddto(), Ward.class);
		entity = modelMapper.map(model, AddressOrder.class);

		entity.setDistrict(district);
		entity.setProvince(province);

		entity.setWard(ward);
		entity.setUser(user);
		
		return entity;
	}
	
	
	
	

}
