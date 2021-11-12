package com.it15306.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.dto.DistrictDTO;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.WardDTO;
import com.it15306.dto.WarehouseDTO;
import com.it15306.entities.AddressOrder;
import com.it15306.entities.District;
import com.it15306.entities.Province;
import com.it15306.entities.Ward;
import com.it15306.entities.Warehouse;
import com.it15306.repository.WarehouseRepository;
import com.it15306.services.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	WarehouseRepository warehouseRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<WarehouseDTO> getAllWarehouses() {
		List<Warehouse> list = warehouseRepository.findAll();
		List<WarehouseDTO> listdto = new ArrayList<WarehouseDTO>();
		if (!list.isEmpty()) {
			list.forEach(warehose -> {
				try {
					WarehouseDTO dto = mapToModel(warehose, null);					
					dto.setProvincedto(modelMapper.map(warehose.getProvince(), ProvinceDTO.class));
					dto.setDistrictdto(modelMapper.map(warehose.getDistrict(), DistrictDTO.class));
					dto.setWarddto(modelMapper.map(warehose.getWard(), WardDTO.class));
					listdto.add(dto);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}

			});
			
		}
		return listdto;
	}

	@Override
	public WarehouseDTO getByIdWarehouse(Integer ware_house_id) {
		Optional<Warehouse> optional = warehouseRepository.findById(ware_house_id);
		WarehouseDTO dto = new WarehouseDTO();
		if (optional.isPresent()) {
			Warehouse warehose=optional.get();
			
				try {
					dto = mapToModel(warehose, null);					
					dto.setProvincedto(modelMapper.map(warehose.getProvince(), ProvinceDTO.class));
					dto.setDistrictdto(modelMapper.map(warehose.getDistrict(), DistrictDTO.class));
					dto.setWarddto(modelMapper.map(warehose.getWard(), WardDTO.class));
					return dto;
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}			
				
		}
		return dto = null;
	}

	@Override
	public WarehouseDTO createWarehouse(WarehouseDTO wareHouse) {
		try {
			Warehouse wh= mapToEnyities(wareHouse, null);
			wh.setProvince(modelMapper.map(wareHouse.getProvincedto(), Province.class));
			wh.setDistrict(modelMapper.map(wareHouse.getDistrictdto(), District.class));
			wh.setWard(modelMapper.map(wareHouse.getWarddto(), Ward.class));
			warehouseRepository.save(wh);
			wareHouse.setWare_house_id(wh.getWare_house_id());
			return wareHouse;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wareHouse = null;
	}

	@Override
	public WarehouseDTO updateWarehouse(WarehouseDTO wareHouse) {
		Optional<Warehouse> optional = warehouseRepository.findById(wareHouse.getWare_house_id());
		Warehouse warehouse2 =new Warehouse();
		if(optional.isPresent()) {
			Warehouse wh=optional.get();
			try {
				warehouse2 = mapToEnyities(wareHouse, wh);
				warehouse2.setProvince(modelMapper.map(wareHouse.getProvincedto(), Province.class));
				warehouse2.setDistrict(modelMapper.map(wareHouse.getDistrictdto(), District.class));
				warehouse2.setWard(modelMapper.map(wareHouse.getWarddto(), Ward.class));				
				warehouseRepository.save(warehouse2);
				return wareHouse;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Integer deleteWarehouse(Integer ware_house_id) {
		Optional<Warehouse> optional = warehouseRepository.findById(ware_house_id);
		if(optional.isPresent()) {
			Warehouse entity =optional.get();
			warehouseRepository.delete(entity);
			return entity.getWare_house_id();
		}
		
		return 0;
	}

	public WarehouseDTO mapToModel(Warehouse enti, WarehouseDTO model)
			throws IllegalAccessException, InvocationTargetException {
		if (model == null)
			model = new WarehouseDTO();
		model = modelMapper.map(enti, WarehouseDTO.class);
		return model;
	}

	public Warehouse mapToEnyities(WarehouseDTO model, Warehouse enti)
			throws IllegalAccessException, InvocationTargetException {
		if (enti == null)
			enti = new Warehouse();
		enti = modelMapper.map(model, Warehouse.class);
		return enti;
	}

}
