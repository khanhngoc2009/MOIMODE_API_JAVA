package com.it15306.repository;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


import com.it15306.entities.Product;
import com.it15306.entities.Product_Sku;
import com.it15306.entities.Sku;


@Repository
public interface ProductSkuRepository extends PagingAndSortingRepository<Product_Sku, Integer>  {
//	final String SELECT_BY_OPTION_VALUE="select s"
//			+ " from sku s join s.product_sku p_sku"
//			+ " where p_sku.product =:product AND"
//			+ " p_sku.status = 1 AND  "
//			+ " s.option_sku_id =:option_1 OR "
//			+ " s.option_sku_id =:option_2 OR"
//			+ " s.option_sku_id =:option_3 "
//			+ " group by s.product_sku "
//			+ " order by count(s) desc limit 1";
//	
//	@Query(SELECT_BY_OPTION_VALUE)
//	Product_Sku findByOptionValue(
//			@Param("product") Product product,
//			@Param("option_1") Integer option_value_1,
//			@Param("option_2") Integer option_value_2,
//			@Param("option_3") Integer option_value_3
//	);
}
