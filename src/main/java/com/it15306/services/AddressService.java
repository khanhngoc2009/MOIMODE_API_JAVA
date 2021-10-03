package com.it15306.services;

import java.util.List;

import com.it15306.entities.AddressOrder;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface AddressService {
	List<AddressOrder> getAllAddressByUserId(String user_id);
	
	AddressOrder getAddressById(String id,String user_id);
	
	AddressOrder saveAddressOrder(AddressOrder address, String user_id);
	
	void delete(String id);
}
