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
//	private String email;
//	private String password;
//	private String username;
//	private Integer admin;
//	private Integer activated;
//	private String photo;
//	private String phone;
	final String SELECT_ORDER_BY_ID_VOUCHER = "SELECT o FROM Order o WHERE o.voucher.id =:id";
	final String SELECT_ORDER = "SELECT * FROM orders join user on user.user_id = orders.user_id"
			+ " WHERE orders.status like %?1% "
			+ " and orders.user_id = ?2 "
			+" and orders.create_date >= ?3 and orders.create_date <= ?4"
			+ " order by orders.create_date desc";
	final String SELECT_ORDER_CANCEL = "SELECT * FROM orders join user on user.user_id = orders.user_id"
			+ " WHERE orders.status >= ?1 "
			+ " and orders.user_id = ?2 "
			+" and orders.create_date >= ?3 and orders.create_date <= ?4"
			+ " order by orders.create_date desc";
	final String COUNT_ORDER_CLIENT = "SELECT count(orders.order_id) FROM orders join user on user.user_id = orders.user_id "
			+ " WHERE orders.status like %?1% "
			+ " and orders.user_id = ?2 "
			+ " and orders.create_date >= ?3 and orders.create_date <= ?4 "
			+ " order by orders.create_date desc";
	final String COUNT_ORDER_CLIENT_CANCEL = "SELECT count(orders.order_id) FROM orders join user on user.user_id = orders.user_id "
			+ " WHERE orders.status >= ?1 "
			+ " and orders.user_id = ?2 "
			+ " and orders.create_date >= ?3 and orders.create_date <= ?4 "
			+ " order by orders.create_date desc";
	final String COUNT_ORDER_STATUS = "SELECT count(orders.order_id) FROM orders join user on user.user_id = orders.user_id "
			+ " WHERE orders.status like %?1% "
			+ " and orders.user_id = ?2 ";
	final String SELECT_ORDER_ADMIN = "SELECT * FROM orders join user on user.user_id = orders.user_id"
			+ " WHERE orders.status like %?1% and orders.order_id like %?7%"
			+ " and email like %?2%"
			+ " and username  like %?3%  "
			+ " and phone like %?4% "
			+ " and orders.create_date >= ?5 and orders.create_date <= ?6 "
			+ " order by orders.create_date desc";
	final String COUNT_ORDER_ADMIN = "SELECT count(order_id) FROM orders join user on user.user_id = orders.user_id"
			+ " WHERE orders.status like %?1% and orders.order_id like %?7%"
			+ " and email like %?2%"
			+ " and username like %?3% "
			+ " and phone like %?4% "
			+" and orders.create_date >= ?5 and orders.create_date <= ?6"
			+" order by orders.create_date desc";
	
	final String SELECT_ORDER_ADMIN_ALL = "SELECT * FROM orders join user on user.user_id = orders.user_id"
			+ " WHERE orders.status >= 1 and orders.status <= 3  and orders.order_id like %?6% "
			+ " and email like %?1%"
			+ " and username  like %?2%  "
			+ " and phone like %?3% "
			+ " and orders.create_date >= ?4 and orders.create_date <= ?5 "
			+ " order by orders.create_date desc";
	final String COUNT_ORDER_ADMIN_ALL = "SELECT count(order_id) FROM orders join user on user.user_id = orders.user_id"
			+ " WHERE orders.status >= 1 and orders.status <= 3 and orders.order_id like %?6% "
			+ " and email like %?1%"
			+ " and username  like %?2%  "
			+ " and phone like %?3% "
			+ " and orders.create_date >= ?4 and orders.create_date <= ?5 "
			+ " order by orders.create_date desc";
	final String ORDER_DETAIL = "SELECT o FROM Order o where o.order_id =:order_id ";

	final String thongKeOrderCount = "SELECT count(o.order_id) FROM Order o where o.status= ?1";
	final String thongKeOrderDoanhThu = "SELECT sum(o.total_price)  FROM Order o where o.status= ?1";
	final String thongKeBienDoDonHang = "select count(order_id) from orders  where status= ?1 and create_date between ?2 and ?3";
	final String thongKeBienDoDoanhThu = "select sum(total_price) from orders  where status= ?1 and create_date between ?2 and ?3";
	
	final String thongKeDoanhThu = "SELECT * from orders where status like %?1% and  create_date between ?2 and ?3 ";
	final String sumDoanhThu ="select sum(total_price) from orders where status like %?1% and create_date between ?2 and ?3";
	final String thongKeDonHang = "SELECT * from orders where status like %?1% and  create_date between ?2 and ?3";
	final String countDonHang ="select count(order_id) from orders where status like %?1%  and create_date between ?2 and ?3";
	
	@Query(SELECT_ORDER_BY_ID_VOUCHER)

	List<Order> findOrderByIdVoucher(@Param("id") Integer id);
	
	@Query(ORDER_DETAIL)
	Order getDetailOrderId(@Param("order_id") Integer order_id);
	
	@Query(value = COUNT_ORDER_CLIENT, nativeQuery = true)
	Integer getCountClient(@Param("status") String status,@Param("user_id") Integer user_id,@Param("startDate") String startDate, @Param("endDate") String endDate );
	
	@Query(value = SELECT_ORDER, nativeQuery = true)
	Page<Order> getListOrders(@Param("status") String status,@Param("user_id") Integer user_id, @Param("startDate") String startDate, @Param("endDate") String endDate , Pageable paging);
	
	@Query(value = COUNT_ORDER_CLIENT_CANCEL, nativeQuery = true)
	Integer getCountClientCancel(@Param("status") String status,@Param("user_id") Integer user_id,@Param("startDate") String startDate, @Param("endDate") String endDate );
	
	@Query(value = SELECT_ORDER_CANCEL, nativeQuery = true)
	Page<Order> getListOrdersCancel(@Param("status") String status,@Param("user_id") Integer user_id, @Param("startDate") String startDate, @Param("endDate") String endDate , Pageable paging);
	
	
	@Query(thongKeOrderCount)
	Integer thongKeOrderCount(@Param("status") Integer status);
	
	@Query(thongKeOrderDoanhThu)
	Float thongKeOrderDoanhThu(@Param("status") Integer status);
	
	@Query(value = thongKeBienDoDonHang, nativeQuery = true)
	Integer thongKeBienDoDonHang(@Param("status") Integer status, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(value = thongKeBienDoDoanhThu, nativeQuery = true)
	Float thongKeBienDoDoanhThu(@Param("status") Integer status, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(value = thongKeDoanhThu, nativeQuery = true)
	Page<Order> thongKeDoanhThu(@Param("status") String status, @Param("startDate") String startDate, @Param("endDate") String endDate, Pageable paging);
	
	@Query(value = sumDoanhThu, nativeQuery = true)
	Float sumDoanhThu(@Param("status") String status, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(value = thongKeDonHang, nativeQuery = true)
	Page<Order> thongKeDonHang(@Param("status") String status, @Param("startDate") String startDate, @Param("endDate") String endDate, Pageable paging);
	
	@Query(value = countDonHang, nativeQuery = true)
	Integer countDonHang(@Param("status") String status, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(value = "select create_date from orders order by create_date asc limit 1", nativeQuery = true)
	String START_DATE();
	@Query(value = "select create_date from orders order by create_date desc limit 1", nativeQuery = true)
	String END_DATE();
	
	
	// admin
	@Query(value = COUNT_ORDER_ADMIN, nativeQuery = true)
	Integer getCountAdmin(@Param("status") String status,@Param("email") String email,@Param("user_name") String user_name,@Param("phone") String phone,@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("id") String id );
	
	@Query(value = SELECT_ORDER_ADMIN, nativeQuery = true)
	Page<Order> getOrdersAdmin(@Param("status") String status,@Param("email") String email,@Param("user_name") String user_name,@Param("phone") String phone,@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("id") String id , Pageable paging );
	
	@Query(value = COUNT_ORDER_STATUS, nativeQuery = true)
	Integer getCountClientStatus(@Param("status") String status,@Param("user_id") Integer user_id);
	
	// admin
		@Query(value = COUNT_ORDER_ADMIN_ALL, nativeQuery = true)
		Integer getCountAdminAll(@Param("email") String email,@Param("user_name") String user_name,@Param("phone") String phone,@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("id") String id  );
		
		@Query(value = SELECT_ORDER_ADMIN_ALL, nativeQuery = true)
		Page<Order> getOrdersAdminAll(@Param("email") String email,@Param("user_name") String user_name,@Param("phone") String phone,@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("id") String id , Pageable paging );
	
}
