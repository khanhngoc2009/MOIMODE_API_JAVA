package com.it15306.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.AddressOrder;
import com.it15306.repository.AddressOrderRepository;
import com.it15306.services.AddressService;

public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressOrderRepository addressOrderRepository;
	
	@Override
	public List<AddressOrder> getAllAddressByUserId(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressOrder getByIdAddressOrder(String id, String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressOrder saveAddressOrder(AddressOrder address, String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
