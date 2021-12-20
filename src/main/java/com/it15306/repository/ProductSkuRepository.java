package com.it15306.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.it15306.entities.Cart;
import com.it15306.entities.Product_Sku;


@Repository
public interface ProductSkuRepository extends JpaRepository<Product_Sku, Integer>  {
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
	final String SELECT_PRODUCT_SKU_BY_ID = "SELECT P FROM Product_Sku  P WHERE P.product_sku_id=:id";
	@Query(SELECT_PRODUCT_SKU_BY_ID)
	Product_Sku findProductSKUById(@Param("id") Integer id);
	
	
	final String SELECT_PRODUCT_SKU = "SELECT P FROM Product_Sku  P join P.product pr"
			+ " WHERE P.value_sku like %?1% and pr.type = 2 and P.status = 1"
			+ " order by P.create_date desc";
	@Query(SELECT_PRODUCT_SKU)
	Page<Product_Sku> findProductSKU(@Param("value_sku") String value_sku,Pageable page);
	
	
	final String COUNT_PRODUCT_SKU_ADMIN = "SELECT count(product_sku_id) FROM Product_Sku  P join P.product pr "
			+ "WHERE P.value_sku like %?1% and pr.type = 2 and P.status = 1";
	@Query(COUNT_PRODUCT_SKU_ADMIN)
	Integer countSku(@Param("value_sku") String value_sku);
	
	final String SELECT_PRODUCT_SKU_BY_PRODUCT_ID = "SELECT P FROM Product_Sku  P WHERE P.product.id=:id";
	@Query(SELECT_PRODUCT_SKU_BY_PRODUCT_ID)
	List<Product_Sku> findByProductId(@Param("id") Integer id);
}
