package com.it15306.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.dto.ProductDTO;
import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.entities.User;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>  {
	final String SELECT_ALL = "SELECT p FROM Product p";
//	final String SELECT_BY_CATEGORY = "SELECT p FROM Product p WHERE p.category =:category";
////	final String SELECT_BY_ID = "SELECT p FROM Product p WHERE p.product_id =:product_id";
//	final String SEARCH = "SELECT p FROM Product p WHERE"
//			+ " p.create_date BETWEEN :start_date AND :end_date "
//			+ "AND p.status = :status "
//			+ "AND p.product_name = :product_name ";
////	
	final String SELECT_PRODUCTS ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku "
			+ " where p.status = 1 "
			+ " group by sku.product";
	final String SELECT_PRODUCT_BY_CATEGORY ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku "
			+ " where p.status = 1 AND p.category =:category"
			+ " group by sku.product";
	final String SELECT_BY_ID ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku "
			+ " where p.status = 1 AND p.product_id =:product_id"
			+ " group by sku.product";
//	\
	
	@Query(SELECT_ALL)
	List<Product> findAllProductsAdmin();
	@Query(SELECT_PRODUCTS)
	List<Object> findAllProduct();
////
//	@Query(SEARCH)
//	List<Product> searchProduct(
//			@Param("start_date") Date start_date,
//			@Param("end_date") Date end_date,
//			@Param("status") Integer status,
//			@Param("product_name") String product_name
//			);
////	
	@Query(SELECT_PRODUCT_BY_CATEGORY)
	List<Object> findProductByCategory(@Param("category") Category category);
//	
	@Query(SELECT_BY_ID)
	Object findByIdProduct(@Param("product_id") Integer id);

}
