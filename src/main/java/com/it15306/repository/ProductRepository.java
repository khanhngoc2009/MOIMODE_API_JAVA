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

import com.it15306.entities.Product;
import com.it15306.entities.User;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>  {
	final String SELECT_ALL = "SELECT p FROM Product p";
	final String SELECT_BY_CATEGORY = "SELECT p FROM Product p WHERE p.category_id =:category_id";
	final String SELECT_BY_ID = "SELECT p FROM Product p WHERE p.product_id =:product_id";
	

	@Query(SELECT_ALL)
	List<Product> findAllProduct();
	
	@Query(SELECT_BY_CATEGORY)
	List<Product> findProductByCategory(@Param("product_id") String id);
	
	@Query(SELECT_BY_ID)
	Product findById(@Param("product_id") String id);

}
