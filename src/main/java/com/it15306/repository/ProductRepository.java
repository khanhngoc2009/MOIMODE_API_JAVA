package com.it15306.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.dto.product.ProductDTO;
import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.entities.User;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>  {
	final String SELECT_ALL = "SELECT p FROM Product p order by p.create_date desc";
	
	final String SELECT_COUNT_ADMIN = "SELECT count(p) FROM Product p ";
	
	final String SELECT_COUNT_CLIENT = 
			"SELECT count(p) FROM Product p "
			+ " where p.status = 1 and p.type= 2";

	
	final String SELECT_PRODUCTS ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku "
			+ " where p.status = 1 "
			+ " group by sku.product"
			+ " order by p.create_date asc";
	final String SELECT_PRODUCT_BY_CATEGORY ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku "
			+ " where p.status = 1 AND p.category =:category"
			+ " group by sku.product"
			+ " order by p.create_date asc";
	
	final String SELECT_PRODUCT_SELLING ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku "
			+ " where p.status = 1 "
			+ " group by sku.product"
			+ " order by max(sku.quantiy_rest) desc";
	
	final String SELECT_BY_ID ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku"
			+ " where p.id =:product_id AND p.status = 1  group by sku.product ";
	
	
	
	@Query(SELECT_ALL)
	Page<Product> findAllProductsAdmin(Pageable page );
	
	@Query(SELECT_COUNT_ADMIN)
	long countProductAdmin();
	
	@Query(SELECT_PRODUCT_SELLING)
	Page<Object> findProductSelling(Pageable page);
	
	@Query(SELECT_PRODUCTS)
	Page<Object> findAllProduct(Pageable page);

	@Query(SELECT_PRODUCT_BY_CATEGORY)
	Page<Object> findProductByCategory(@Param("category") Category category,Pageable page);

	@Query(SELECT_BY_ID)
	Object findByIdProduct(@Param("product_id") Integer product_id);

	@Query(SELECT_COUNT_CLIENT)
	long countProductClient();

	
}
