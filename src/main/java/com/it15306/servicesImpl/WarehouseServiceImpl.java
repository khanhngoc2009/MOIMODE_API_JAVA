package com.it15306.servicesImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.dto.WarehouseDTO;
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
		System.out.println("----------------1: "+list.size());
		if (!list.isEmpty()) {
			list.forEach(warehose -> {
				try {
					WarehouseDTO dto = mapToModel(warehose, null);
					System.out.println("----------------2"+dto.toString());
					listdto.add(dto);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}

			});
			return listdto;
		}
		return null;
	}

	@Override
	public WarehouseDTO getByIdWarehouse(Integer ware_house_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WarehouseDTO createWarehouse(WarehouseDTO wareHouse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WarehouseDTO updateWarehouse(WarehouseDTO wareHouse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteWarehouse(Integer ware_house_id) {
		// TODO Auto-generated method stub
		return null;
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
