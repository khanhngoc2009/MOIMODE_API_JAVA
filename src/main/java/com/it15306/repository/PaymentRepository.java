package com.it15306.repository;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>  {
	final String SELECT_ALL = "SELECT p FROM Payment p";
	final String SELECT_BY_NAME = "SELECT p FROM Payment p WHERE p.name =:name";
	final String SELECT_BY_STATUS = "SELECT p FROM Payment p WHERE p.status =:status";
	final String SELECT_BY_TYPE = "SELECT p FROM Payment p WHERE p.type =:type";

	@Query(SELECT_ALL)
	List<Payment> findAllPayment();
	
	@Query(SELECT_BY_NAME)
	Payment findByName(@Param("name") String name);
	
	@Query(SELECT_BY_STATUS)
	Payment findByStatus(@Param("status") String status);
	
	@Query(SELECT_BY_TYPE)
	Payment findByType(@Param("type") Integer type);
	
}
