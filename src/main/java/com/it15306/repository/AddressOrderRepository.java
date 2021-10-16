package com.it15306.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.AddressOrder;
import com.it15306.entities.User;


@Repository
public interface AddressOrderRepository extends JpaRepository<AddressOrder, Integer>  {
	final String SELECT_ALL = "SELECT u FROM AddressOrder u";
	final String SELECT_AddressOrderByID = "SELECT u FROM AddressOrder u where user_id=:user_id";
	final String SELECT_AddressOrderByAddressOrderId = "SELECT u FROM AddressOrder u where address_order_id=:address_order_id";
	
	@Query(SELECT_ALL)
	List<AddressOrder> findAllAddressOrder();
	
	@Query(SELECT_AddressOrderByID)
	List<AddressOrder> findAddressOrderByUserID(@Param("user_id") String user_id);
	
	@Query(SELECT_AddressOrderByAddressOrderId)
	AddressOrder findAddressOrderByOrderAddressId(@Param("address_order_id") Integer address_order_id);
	
	


}
