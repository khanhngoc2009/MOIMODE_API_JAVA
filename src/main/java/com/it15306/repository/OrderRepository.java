package com.it15306.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	final String SELECT_ORDER = "SELECT o FROM Order o"
			+ " WHERE o.status =:status "
			+ " and o.user.id = :user_id "
			+ " order by create_date desc";
	
	@Query(SELECT_ORDER_BY_ID_VOUCHER)
	List<Order> findOrderByIdVoucher(@Param("id") Integer id);
	
	@Query(SELECT_ORDER)
	Page<Order> getListOrders( Pageable paging,@Param("status") Integer status,@Param("user_id") Integer user_id);
}
