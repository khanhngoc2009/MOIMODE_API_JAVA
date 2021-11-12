package com.it15306.repository;

import com.it15306.entities.Option_Product;
import com.it15306.entities.Options;
import com.it15306.entities.Product;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface OptionProductsRespository extends PagingAndSortingRepository<Option_Product, Integer>  {

	final String SELECT_BY_ID ="select p, o "
			+ " from Option_Product p join p.option o "
			+ " where p.product =:product";
	 @Modifying
     @Query(value = "insert into options_products (option_id,product_id) VALUES (:option_id,:product_id)", nativeQuery = true)
     @Transactional
     void saveValue(@Param("option_id") Integer option_id, @Param("product_id") Integer product_id);
	
	final String SELECT_BY_OPTION ="select p  "
			+ " from Option_Product p"
			+ " where p.option =:option";

	@Query(SELECT_BY_ID)
	Object findByIdProduct(@Param("product") Product product);
	
	@Query(SELECT_BY_OPTION)
	Option_Product findByIdOption(@Param("option") Options option);

}