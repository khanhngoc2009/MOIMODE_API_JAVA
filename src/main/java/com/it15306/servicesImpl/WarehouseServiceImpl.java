package com.it15306.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.Warehouse;
import com.it15306.repository.WarehouseRepository;
import com.it15306.services.WarehouseService;

public class WarehouseServiceImpl implements WarehouseService{
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Override
	public List<Warehouse> getAllWarehouses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Warehouse getByIdWarehouse(String ware_house_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Warehouse saveUser(Warehouse wareHouse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String ware_house_id) {
		// TODO Auto-generated method stub
		
	}

}
