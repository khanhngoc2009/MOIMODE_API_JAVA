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

import com.it15306.entities.Options;
import com.it15306.entities.Product;
import com.it15306.entities.User;


@Repository
public interface OptionProductRepository extends JpaRepository<Options, Integer>  {
//	final String SELECT_ALL = "SELECT op FROM OptionProduct op WHERE op.product=:product";
//	
//	@Query(SELECT_ALL)
//	List<Options> findAllOptionProductByProduct(@Param("product") Product product);
	
}
