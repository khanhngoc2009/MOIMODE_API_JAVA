package com.it15306.services;

import java.util.List;

//import com.it15306.entities.Category;
import com.it15306.entities.User;
import com.it15306.entities.Warehouse;

public interface WarehouseService {
	List<Warehouse> getAllWarehouses();
	Warehouse getByIdWarehouse(String ware_house_id);

	Warehouse saveUser(Warehouse wareHouse);

	void delete(String ware_house_id);
}
