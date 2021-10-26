package com.it15306.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.it15306.entities.Category;
import com.it15306.entities.Option_Product;
import com.it15306.entities.Options;
import com.it15306.entities.Product;


@Repository
public interface OptionProductsRespository extends PagingAndSortingRepository<Option_Product, Integer>  {

	final String SELECT_BY_ID ="select p, o "
			+ " from Option_Product p join p.option o "
			+ " where p.product =:product";
	
	
	final String SELECT_BY_OPTION ="select p  "
			+ " from Option_Product "
			+ " where p.option =:option";

	@Query(SELECT_BY_ID)
	Object findByIdProduct(@Param("product") Product product);
	
	@Query(SELECT_BY_OPTION)
	Option_Product findByIdOption(@Param("opiton") Options option);

}