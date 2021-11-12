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

import com.it15306.entities.Order;
import com.it15306.entities.User;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>  {
	final String SELECT_ORDER_BY_ID_VOUCHER = "SELECT o FROM Order o WHERE o.voucher.id =:id";
	
	
	@Query(SELECT_ORDER_BY_ID_VOUCHER)
	List<Order> findOrderByIdVoucher(@Param("id") Integer id);
	
	
}
