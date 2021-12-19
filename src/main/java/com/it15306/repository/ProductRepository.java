package com.it15306.repository;

import com.it15306.entities.Category;
import com.it15306.entities.Product;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;



@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>  {
//	final String SELECT_ALL = "SELECT p FROM Product p"
//			+ " where p.category.id like %:category_id% "
//			+ " order by p.create_date desc";
	final String SELECT_ALL = "select * from product \r\n"
			+ "where category_id like CONCAT('%', ?1, '%') "
			+ "and create_date >= ?2 "
			+ "and create_date <= ?3 and product_name like CONCAT('%', ?4, '%')"
			+ " and status like CONCAT('%', ?5, '%') order by create_date desc";
	final String SELECT_COUNT_ADMIN_QUERY = "select count(*) from product \r\n"
			+ "where category_id like CONCAT('%', ?1, '%') "
			+ "and create_date >= ?2 "
			+ "and create_date <= ?3 and product_name like CONCAT('%', ?4, '%')"
			+ " and status like CONCAT('%', ?5, '%')";
	
	final String SELECT_COUNT_ADMIN = "SELECT count(p) FROM Product p ";
	
	final String SELECT_COUNT_CLIENT = 
			"SELECT count(p) FROM Product p "
			+ " where p.status = 1 and p.type= 2";

	
	final String SELECT_PRODUCTS ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku "
			+ " where p.status = 1 and p.type = 2  "
			+ " group by sku.product"
			+ " order by p.create_date desc";
	final String SELECT_PRODUCT_BY_CATEGORY ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku "
			+ " where p.status = 1 and p.type = 2 AND p.category =:category"
			+ " group by sku.product"
			+ " order by p.create_date desc";
	
	final String SELECT_PRODUCT_SELLING ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku "
			+ " where p.status = 1 and p.type = 2 "
			+ " group by sku.product"
			+ " order by max(sku.quantiy_rest) desc";
	
	final String SELECT_BY_ID ="select p,min(sku.price),max(sku.price)"
			+ " from Product p join p.product_sku sku"
			+ " where p.id =:product_id AND p.status = 1 and p.type = 2  group by sku.product ";
	
	
	
	
//	select * from product 
//	where category_id like '%1%' and create_date between '2021-10-26' and '2021-11-02' and product_name like '%%'
	final String SELECT_CATEGORY_ID ="select c from Product c where c.category.category_parent_id=:id";
	
	final String thongKeCountProduct ="select COUNT(p.id) from Product p where p.status= ?1";
	
	
	@Query( value = SELECT_ALL,
			  nativeQuery = true)
	Page<Object> findAllProductsAdmin(Pageable page ,
			@Param("category_id") String category_id,
			@Param("start_date") String start_date,
			@Param("end_date") String end_date,
			@Param("name") String name,
			@Param("status") String status
			);
	
	@Query(value = SELECT_COUNT_ADMIN_QUERY,
			  nativeQuery = true)
	BigInteger countAdminQuery(
			@Param("category_id") String category_id,
			@Param("start_date") String start_date,
			@Param("end_date") String end_date,
			@Param("name") String name,
			@Param("status") String status
			);
	
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
	
	
	@Query(SELECT_CATEGORY_ID)
	List<Product> findByCategoryIds(@Param("id")Integer id);
	
	@Query(thongKeCountProduct)
	Integer thongKeCountProduct(@Param("status") Integer status);
	
	@Query(value="select count(product_id) from product  where category_id= ?1", nativeQuery = true)
	Integer countProductByCategory(@Param("category_id") Integer category_id);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// FILTER CATEGORY
	final String SELECT_PRODUCT_BY_CATEGORY_FILTER_PRICE_RAISE ="select p,min(sku.price),max(sku.price) from Product p"
			+ " join p.product_sku sku "
			+ " where"
			+ " sku.price >= ?1 "
			+ " and sku.price <= ?2 "
			+ " and p.status = 1 "
			+ " and p.type = 2 "
			+ " and p.category.id  = ?3 "
			+ " group by sku.product "
			+ " order by min(sku.price) asc";
	final String SELECT_PRODUCT_BY_CATEGORY_FILTER_PRICE_DECREASE ="select p,min(sku.price),max(sku.price) from Product p"
			+ " join p.product_sku sku "
			+ " where"
			+ " sku.price >= ?1 "
			+ " and sku.price <= ?2 "
			+ " and p.status = 1 "
			+ " and p.type = 2 "
			+ " and p.category.id  = ?3 "
			+ " group by sku.product "
			+ " order by min(sku.price) desc";
	
	final String SELECT_PRODUCT_BY_CATEGORY_FILTER_NAME_RAISE ="select p,min(sku.price),max(sku.price) from Product p"
			+ " join p.product_sku sku "
			+ " where"
			+ " sku.price >= ?1 "
			+ " and sku.price <= ?2 "
			+ " and p.status = 1 "
			+ " and p.type = 2 "
			+ " and p.category.id = ?3 "
			+ " group by sku.product "
			+ " order by p.product_name asc ";
	final String SELECT_PRODUCT_BY_CATEGORY_FILTER_ALL ="select p,min(sku.price),max(sku.price) from Product p"
			+ " join p.product_sku sku "
			+ " where"
			+ " sku.price >= ?1 "
			+ " and sku.price <= ?2 "
			+ " and p.status = 1 "
			+ " and p.type = 2 "
			+ " and p.category.id = ?3 "
			+ " group by sku.product "
			+ " order by p.create_date asc";
	@Query(SELECT_PRODUCT_BY_CATEGORY_FILTER_PRICE_RAISE)
	Page<Object> findProductByCategoryPriceRaise(@Param("min_price") double min_price,@Param("max_price") double max_price,@Param("category") Integer category,Pageable page);
	@Query(SELECT_PRODUCT_BY_CATEGORY_FILTER_PRICE_DECREASE)
	Page<Object> findProductByCategoryPriceDECREASE(@Param("min_price") double min_price,@Param("max_price") double max_price,@Param("category") Integer category,Pageable page);
	@Query(SELECT_PRODUCT_BY_CATEGORY_FILTER_NAME_RAISE)
	Page<Object> findProductByCategoryNameRaise(@Param("min_price") double min_price,@Param("max_price") double max_price,@Param("category") Integer category,Pageable page);
	@Query(SELECT_PRODUCT_BY_CATEGORY_FILTER_ALL)
	Page<Object> findProductByCategoryAll(@Param("min_price") double min_price,@Param("max_price") double max_price,@Param("category") Integer category,Pageable page);
	
	final String SELECT_COUNT_CLIENT_CATEGORY = 
			"select p,min(sku.price),max(sku.price) from Product p"
					+ " join p.product_sku sku "
					+ " where"
					+ " sku.price >= ?1 "
					+ " and sku.price <= ?2 "
					+ " and p.status = 1 "
					+ " and p.type = 2 "
					+ " and p.category.id = ?3 "
					+ " group by sku.product "
					+ " order by p.create_date asc";
	@Query(SELECT_PRODUCT_BY_CATEGORY_FILTER_ALL)
	List<Object> countProductClientCategory(
			@Param("min_price") double min_price,
			@Param("max_price") double max_price,
			@Param("category") Integer category
	);
	
	
	
	
	
	
	
	
	
	
}
