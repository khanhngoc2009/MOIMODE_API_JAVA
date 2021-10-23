package com.it15306.services;

import java.util.List;

import com.it15306.dto.WarehouseDTO;

public interface WarehouseService {
	List<WarehouseDTO> getAllWarehouses();
	WarehouseDTO getByIdWarehouse(Integer ware_house_id);
	WarehouseDTO createWarehouse(WarehouseDTO wareHouse);
	WarehouseDTO updateWarehouse(WarehouseDTO wareHouse);
	Integer deleteWarehouse(Integer ware_house_id);
}
