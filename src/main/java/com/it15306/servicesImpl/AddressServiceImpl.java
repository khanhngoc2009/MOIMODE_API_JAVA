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
import com.it15306.dto.addressOrder.BodyAddressOrder;
import com.it15306.entities.AddressOrder;
import com.it15306.entities.District;
import com.it15306.entities.Province;
import com.it15306.entities.User;
import com.it15306.entities.Ward;
import com.it15306.repository.AddressOrderRepository;
import com.it15306.repository.DistrictRepository;
import com.it15306.repository.ProvinceRepository;
import com.it15306.repository.UserRepository;
import com.it15306.repository.WardRepository;
import com.it15306.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressOrderRepository addressOrderRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
	private WardRepository wardRepository;

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
	public BodyAddressOrder createAddressOrder(BodyAddressOrder data) {
		AddressOrder entity=new AddressOrder();
		AddressOrderDTO dto=new AddressOrderDTO();
		User user= userRepository.getOne(data.getUser_id());
		Ward ward=wardRepository.getOne(data.getWard_id());
		Province province = provinceRepository.getOne(data.getProvince_id());
		District district=districtRepository.getOne(data.getDistrict_id());
		entity.setName_persion(data.getName_persion());
		entity.setPhone_persion(data.getPhone_persion());
		entity.setAddress_detail(data.getAddress_detail());
		entity.setCreate_date(new Date());
		entity.setDistrict(district);
		entity.setIsactive(data.getIsActive());
		entity.setIsdefault(data.getIsDefault());
		entity.setProvince(province);
		entity.setStatus(1);
		entity.setWard(ward);
		entity.setUser(user);
		addressOrderRepository.save(entity);
		data.setId(entity.getId());
		return data;

	}

	@Override
	public Integer deleteAddressOrder(Integer address_order_id) {
		Optional<AddressOrder> optional  = addressOrderRepository.findById(address_order_id);
		if (optional .isPresent()) {
			System.out.println("vooooooooooo");
			AddressOrder addressOrder=optional .get();
			addressOrderRepository.delete(addressOrder);
			
			return addressOrder.getId();
		}
		System.out.println("rooooooooooo");
		return 0;
	}

	@Override
	public BodyAddressOrder updateAddressOrder(BodyAddressOrder data) {
		try {
			BodyAddressOrder respon=new BodyAddressOrder();
			AddressOrderDTO dto=new AddressOrderDTO();
			User user= userRepository.getOne(data.getUser_id());
			Ward ward=wardRepository.getOne(data.getWard_id());
			Province province = provinceRepository.getOne(data.getProvince_id());
			District district=districtRepository.getOne(data.getDistrict_id());
		Optional<AddressOrder> optional=addressOrderRepository.findById(data.getId());
		if(optional.isPresent()) {

			AddressOrder addressOrder=optional.get();
			dto.setId(data.getId());
			dto.setName_persion(data.getName_persion());
			dto.setPhone_persion(data.getPhone_persion());
			dto.setAddress_detail(data.getAddress_detail());
			dto.setCreate_date(new Date());
			dto.setDistrictdto(modelMapper.map(district, DistrictDTO.class));
			dto.setIsActive(data.getIsActive());
			dto.setIsDefault(data.getIsDefault());
			dto.setProvincedto(modelMapper.map(province,ProvinceDTO.class));
			dto.setStatus(1);
			dto.setWarddto(modelMapper.map(ward, WardDTO.class));
			dto.setUserdto(modelMapper.map(user,UserDTO.class));
			
			AddressOrder address =	convertToEntity(dto, addressOrder);
			addressOrderRepository.save(address);
			respon.setId(address.getId());
			respon.setName_persion(address.getName_persion());
			respon.setAddress_detail(address.getAddress_detail());
			respon.setIsActive(address.getIsactive());
			respon.setIsDefault(address.getIsdefault());
			respon.setPhone_persion(address.getPhone_persion());
			respon.setProvince_id(address.getProvince().getProvince_id());
			respon.setUser_id(address.getUser().getId());
			respon.setWard_id(address.getWard().getWard_id());
			respon.setDistrict_id(address.getDistrict().getDistrict_id());
			return  respon;
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
