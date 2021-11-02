package com.it15306.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.it15306.entities.Option_Sku_Value;
import com.it15306.entities.Product;
import com.it15306.entities.Product_Sku;
import com.it15306.entities.Sku;


@Repository
public interface SkuRepository extends PagingAndSortingRepository<Sku, Integer>  {
//	final String SELECT_BY_OPTION_VALUE=
//			" select p_sku from Sku sk"
//			+ " join sk.product_sku p_sku"
//			+ " where p_sku.product =:product "
//			+ "AND"
//			+ " p_sku.status = 1 AND "
//			+ " sk.option_sku =:option_1 OR "
//			+ " sk.option_sku =:option_2 OR"
//			+ " sk.option_sku =:option_3 "
//			+ " group by sk.product_sku "
//			+ " order by count(sk) desc";
	
	
	final String SELECT_BY_OPTION_VALUES=
			"select sku.product_sku_id,product_id,value_sku,price,quantity_remain,quantiy_rest,quantity_total,status,url_media from sku \r\n"
			+ "join product_sku on product_sku.product_sku_id = sku.product_sku_id\r\n"
			+ "where product_id = :product_id and option_sku_id = :option_1 or option_sku_id = :option_2 or option_sku_id = :option_3   group by sku.product_sku_id\r\n"
			+ "order by count(*) desc limit 1";

	
	 @Modifying
     @Query(value = "insert into sku (product_sku_id,option_sku_id) VALUES (:product_sku_id,:option_sku_id)", nativeQuery = true)
     @Transactional
     void saveValue(@Param("product_sku_id") Integer product_sku_id, @Param("option_sku_id") Integer option_sku_id);
	 
	 
//	@Query(SELECT_BY_OPTION_VALUE)
	@Query(
			  value = SELECT_BY_OPTION_VALUES,
			  nativeQuery = true)
			Object findByOptionValue(
			@Param("product_id") Integer product_id,
			@Param("option_1") Integer option_value_1,
			@Param("option_2") Integer option_value_2,
			@Param("option_3") Integer option_value_3
	);
}
