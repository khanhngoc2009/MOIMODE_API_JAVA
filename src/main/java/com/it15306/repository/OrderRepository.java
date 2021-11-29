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
	final String COUNT_ORDER_CLIENT = "SELECT count(o.order_id) FROM Order o"
			+ " WHERE o.status =?1 "
			+ " and o.user.id = ?2 "
			+ " order by create_date desc";
	final String ORDER_DETAIL = "SELECT o FROM Order o where o.order_id =:order_id ";

	final String thongKeOrderCount = "SELECT count(o.order_id) FROM Order o where o.status= ?1";
	final String thongKeOrderDoanhThu = "SELECT sum(o.total_price)  FROM Order o where o.status= ?1";
	final String thongKeBienDoDonHang = "select count(order_id) from orders  where status= ?1 and create_date between ?2 and ?3";
	final String thongKeBienDoDoanhThu = "select sum(total_price) from orders  where status= ?1 and create_date between ?2 and ?3";
	
	@Query(SELECT_ORDER_BY_ID_VOUCHER)

	List<Order> findOrderByIdVoucher(@Param("id") Integer id);
	
	@Query(ORDER_DETAIL)
	Order getDetailOrderId(@Param("order_id") Integer order_id);
	
	@Query(COUNT_ORDER_CLIENT)
	Integer getCountClient(@Param("status") Integer status,@Param("user_id") Integer user_id);
	
	@Query(SELECT_ORDER)
	Page<Order> getListOrders( Pageable paging,@Param("status") Integer status,@Param("user_id") Integer user_id);
	
	@Query(thongKeOrderCount)
	Integer thongKeOrderCount(@Param("status") Integer status);
	
	@Query(thongKeOrderDoanhThu)
	Float thongKeOrderDoanhThu(@Param("status") Integer status);
	
	@Query(value = thongKeBienDoDonHang, nativeQuery = true)
	Integer thongKeBienDoDonHang(@Param("status") Integer status, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(value = thongKeBienDoDoanhThu, nativeQuery = true)
	Float thongKeBienDoDoanhThu(@Param("status") Integer status, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
