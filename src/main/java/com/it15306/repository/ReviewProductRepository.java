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

import com.it15306.entities.ReviewProduct;
import com.it15306.entities.User;


@Repository
public interface ReviewProductRepository extends JpaRepository<ReviewProduct, Integer>  {
		
	//final String SELECT_REVIEWPRODUCT_BY_IDPRODUCT = "SELECT r FROM ReviewProduct r where r.";

}
