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
import com.it15306.entities.ProductSkuValues;
import com.it15306.entities.User;


@Repository
public interface ProductSkuValuesRepository extends JpaRepository<ProductSkuValues, Integer>  {
	final String FIND = "SELECT psv FROM ProductSkuValues psv"
			+ " where psv.option_value_id_1=:option_value_id_1"
			+ " and psv.option_value_id_2=:option_value_id_2"
			+ " and psv.option_value_id_3=:option_value_id_3 "
			+ "and psv.product=:product";
	@Query(FIND)
	ProductSkuValues findAllProductSkuValues(
			@Param("option_value_id_1") Integer option_value_id_1,
			@Param("option_value_id_2") Integer option_value_id_2,
			@Param("option_value_id_3") Integer option_value_id_3,
			@Param("product") Product product
			);

}
