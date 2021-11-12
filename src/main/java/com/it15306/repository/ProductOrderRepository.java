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

import com.it15306.entities.OptionValue;
import com.it15306.entities.Options;
import com.it15306.entities.Order;
import com.it15306.entities.ProductOrder;
import com.it15306.entities.User;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer>  {
	final String SELECT_BY_ORDER_ID = " select p from ProductOrder p where p.order.id = :order_id ";
			
	@Query(SELECT_BY_ORDER_ID)
	List<ProductOrder> getByOrderId(@Param("order_id") Integer order_id);
}
