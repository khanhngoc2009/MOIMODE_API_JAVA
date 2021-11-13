package com.it15306.services;

import java.util.List;
import java.util.Optional;

import com.it15306.dto.AddressOrderDTO;
import com.it15306.dto.addressOrder.BodyAddressOrder;
import com.it15306.entities.AddressOrder;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface AddressService {
	List<AddressOrderDTO> getAllAddressByUserId(String user_id);
	
	AddressOrderDTO getAddressOrderById(Integer address_order_id);
		
	BodyAddressOrder createAddressOrder(BodyAddressOrder addressOrderDTO);
	BodyAddressOrder updateAddressOrder(BodyAddressOrder addressOrderDTO);
	Integer deleteAddressOrder(Integer  address_order_id);

	List<AddressOrderDTO> getAllAddressOrder();
}
