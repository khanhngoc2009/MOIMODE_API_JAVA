package com.it15306.repository;

import java.util.Date;
import java.util.List;
//import java.util.Optional;
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

import com.it15306.entities.Voucher;


@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer>  {
	final String SELECT_ALL = "SELECT v FROM Voucher v where v.status = 1";
	final String SELECT_BY_Id = "SELECT v FROM Voucher v WHERE  v.status = 1 and v.id =:id";
	final String SELECT_BY_TITLE = "SELECT v FROM Voucher v WHERE v.title =:title";
	final String SELECT_BY_TYPE_DISCOUNT = "SELECT v FROM Voucher v WHERE v.type_discount =:type_discount";
	final String SELECT_BY_STATUS = "SELECT v FROM Voucher v WHERE v.status =:status";
	final String SELECT_BY_BETWEEN_TIME = "SELECT v FROM Voucher v WHERE v.start_time >= :start_time and v.end_time <= :end_time";
	
	final String SELECT_ALL_TYPE_PAGE = "SELECT v FROM Voucher v where v.status = 1";
	
	final String FINTER ="select * from voucher  where  title like %?1% and create_time >= ?2 -1 and end_time <= ?3 and status like %?4% ORDER BY start_time desc" ;
	final String FINTER_CUSTOMER ="select * from voucher where status = 1 and start_time < ?1 and end_time > ?1 ORDER BY start_time desc";
			
	@Query(SELECT_ALL)
	List<Voucher> findAllVoucher();
	
	@Query(SELECT_ALL_TYPE_PAGE)
	Page<Voucher> findAllVoucherByTypePage(Pageable page);
	
	@Query(SELECT_BY_Id)
	Optional<Voucher> findVoucherById(@Param("id") Integer id);
	
	@Query(SELECT_BY_TITLE)
	Voucher findByTitle(@Param("title") String title);
	
	@Query(SELECT_BY_TYPE_DISCOUNT)
	List<Voucher> findByTypeDiscount(@Param("type_discount") Integer type_discount);
	
	@Query(SELECT_BY_STATUS)
	List<Voucher> findByStatus(@Param("status") Integer status);
	
	@Query(SELECT_BY_BETWEEN_TIME)
	List<Voucher> findByBetweenTime(@Param("start_time") Date start_time, @Param("end_time") Date end_time);
	
	@Query(value=FINTER, nativeQuery = true )
	Page<Voucher> locVoucher(@Param("title") String title, @Param("create_time") String create_time, @Param("end_time") String end_time,@Param("status") String status, Pageable page);
	
	
	@Query(value=FINTER_CUSTOMER, nativeQuery = true )
	Page<Voucher> locVoucherCustomer(@Param("today") String today,Pageable page);
	
	
	@Query(value = "select create_time from voucher order by create_time asc limit 1", nativeQuery = true)
	String START_DATE();
	@Query(value = "select end_time from voucher order by end_time desc limit 1", nativeQuery = true)
	String END_DATE();
}
