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

import com.it15306.entities.OptionProduct;
import com.it15306.entities.User;


@Repository
public interface OptionProductRepository extends JpaRepository<OptionProduct, Integer>  {
	final String SELECT_ALL = "SELECT op  FROM OptionProduct op WHERE op.product_id=:product_id";
	
	@Query(SELECT_ALL)
	List<OptionProduct> findAllOptionProductByProductId(@Param("product_id") String id);
	
}
