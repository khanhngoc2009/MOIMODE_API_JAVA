package com.it15306.repository;

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

import com.it15306.entities.Category;
import com.it15306.entities.ReviewProduct;
import com.it15306.entities.User;


@Repository
public interface ReviewProductRepository extends JpaRepository<ReviewProduct, Integer>  {
		
	final String SELECT_REVIEWPRODUCT_BY_IDPRODUCT = "SELECT r FROM ReviewProduct r where r.products.id=:id";
	
	final String SELECT_PRODUCT_FILLTER = "select * from review_product where status like %?1%  and create_date >= ?2 and create_date <= ?3 ORDER BY create_date desc";
	
	@Query(SELECT_REVIEWPRODUCT_BY_IDPRODUCT)
	List<ReviewProduct> findAllReviewProductsByProductId(@Param("id") Integer id);
	
	@Query(value =SELECT_PRODUCT_FILLTER, nativeQuery = true )
	Page<ReviewProduct> locProduct(@Param("status") String status, @Param("create_date") String create_date,  @Param("end_date") String end_date, Pageable page );


	
	@Query(value = "select create_date from review_product order by create_date asc limit 1", nativeQuery = true)
	String START_DATE();
	@Query(value = "select create_date from review_product order by create_date desc limit 1", nativeQuery = true)
	String END_DATE();
}
