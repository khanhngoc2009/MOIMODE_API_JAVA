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

import com.it15306.entities.ProductSkuValues;
import com.it15306.entities.User;


@Repository
public interface ProductSkuValuesRepository extends JpaRepository<User, Integer>  {
	final String SELECT_ALL = "SELECT psv FROM ProductSkuValues"
			+ " where psv.option_value_id_1=:option_value_id_1"
			+ " and psv.option_value_id_2=:option_value_id_2"
			+ " and psv.option_value_id_3=:option_value_id_3 "
			+ "and product_id=:product_id";

	@Query(SELECT_ALL)
	List<ProductSkuValues> findAllProductSkuValues(
			@Param("option_value_id_1") String option_value_id_1,
			@Param("option_value_id_2") String option_value_id_2,
			@Param("option_value_id_3") String option_value_id_3,
			@Param("product_id") String product_id
			);

}
