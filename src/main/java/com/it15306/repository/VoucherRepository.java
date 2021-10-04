package com.it15306.repository;

import java.util.Date;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

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
	final String SELECT_ALL = "SELECT v FROM Voucher v";
	final String SELECT_BY_Id = "SELECT v FROM Voucher v WHERE v.voucher_id =:voucher_id";
	final String SELECT_BY_TITLE = "SELECT v FROM Voucher v WHERE v.title =:title";
	final String SELECT_BY_TYPE_DISCOUNT = "SELECT v FROM Voucher v WHERE v.type_discount =:type_discount";
	final String SELECT_BY_STATUS = "SELECT v FROM Voucher v WHERE v.status =:status";
	final String SELECT_BY_BETWEEN_TIME = "SELECT v FROM Voucher v WHERE v.start_time >= :start_time and v.end_date <= :end_date";
	

	@Query(SELECT_ALL)
	List<Voucher> findAllVoucher();
	
	@Query(SELECT_BY_Id)
	Optional<Voucher> findById(@Param("voucher_id") Integer voucher_id);
	
	@Query(SELECT_BY_TITLE)
	Voucher findByTitle(@Param("title") String title);
	
	@Query(SELECT_BY_TYPE_DISCOUNT)
	List<Voucher> findByTypeDiscount(@Param("type_discount") Integer type_discount);
	
	@Query(SELECT_BY_STATUS)
	List<Voucher> findByStatus(@Param("status") Integer status);
	
	@Query(SELECT_BY_BETWEEN_TIME)
	List<Voucher> findByBetweenTime(@Param("start_time") Date start_time, @Param("end_date") Date end_date);

}
