package com.it15306.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer>  {
	final String SELECT_ALL = "SELECT c FROM Category c";
	final String SELECT_BY_NAME = "SELECT c FROM Category c WHERE c.name =:name";
	final String SELECT_BY_PARENT_ID = "SELECT c FROM Category c WHERE c.type =:type AND c.category_parent_id =:category_parent_id  ";
	final String SELECT_BY_TYPE = "SELECT c FROM Category c WHERE c.type =:type ORDER BY c.create_date desc";
	final String SELECT_BETWEEN_CREATE_DATE = "SELECT c FROM Category c WHERE c.create_date BETWEEN :ngay_bat_dau AND :ngay_ket_thuc";
	final String SELECT_BY_STATUS = "SELECT c FROM Category c WHERE c.status =:status";
	final String SELECT_ALL_CATEGORY_PARENT ="select C from Category C where C.type ='1' ORDER BY C.create_date desc";
	final String COUNT_BY_TYPE = "SELECT count(category_id) FROM Category c WHERE c.type =:type";
	final String COUNT_CATEGORY_PARENT_BY_ID = "SELECT count(category_id) FROM Category c WHERE c.type = 2 and c.category_parent_id =:id";
//	final String START_DATE="select C.create_date from Category C order by C.create_date desc limit 1";
//	final String END_DATE="select C.create_date from Category C order by C.create_date asc limit 1";
//	loc list
	final String SELECT_CATEGORY_BY_ID ="SELECT c FROM Category c WHERE c.id =:id";

	
	final String SELECT_ALL_FILTER ="SELECT * FROM category c where c.category_name like %?1% and "
									+ " c.create_date  between ?2 "
									+ "and ?3 and status like %?4% and c.type like %?5% ORDER BY c.create_date desc";
	
	final String SELECT_ALL_FILTER_2 ="SELECT * FROM category c where c.category_name like %?1% and "
			+ "c.category_parent_id like %?2% and c.create_date  between ?3 "
			+ "and ?4 and status like %?5% and c.type like %?6% ORDER BY c.create_date desc";
	
	final String test="SELECT * FROM category c where c.category_name like  %?1% ";
	
	final String SELECT_CATEGORY_BY_ID_AND_TYPE ="SELECT * FROM category where category_id =  ?1 and type = ?2 ";
//	final String SELECT_ALL_FILTER ="SELECT * FROM category c where c.category_name like '%:name%' and "
//			+ "c.category_id like '%:parentID' and c.create_date  between :startDate "
//			+ "and :endDate and status like '%:status%' and c.type like '%:type%'";
//	select * from category where category_name like '%%'  
//	AND type  LIKE '%1%' and  create_date  between '2021-10-25' and '2021-12-25' and status like '%%' AND category_id like '%21';
//	
	
	@Query(SELECT_ALL)
	List<Category> findAllCategory();
	
	@Query(SELECT_BY_NAME)
	Category findByName(@Param("name") String category_name);
	
	@Query(SELECT_BY_PARENT_ID)
	Page<Category> findByCategoryParentId(@Param("category_parent_id") Integer category_parent_id,@Param("type") Integer type, Pageable page);
	
	@Query(SELECT_BY_TYPE)
	Page<Category> findByType(@Param("type") Integer type, Pageable page);
	
	@Query(SELECT_BY_TYPE)
	Page<Category> findByTypePage(@Param("type") Integer type, Pageable page);
	
	@Query(SELECT_BETWEEN_CREATE_DATE)
	List<Category> findByCreateDate(@Param("ngay_bat_dau") Date ngay_bat_dau, @Param("ngay_ket_thuc") Date ngay_ket_thuc);
	
	@Query(SELECT_BY_STATUS)
	List<Category> findByStatus(@Param("status") Integer status);
	
	@Query(value = SELECT_ALL_CATEGORY_PARENT)
	Page<Category> selectAllCategoryParent(Pageable page);
	

	@Query(COUNT_BY_TYPE)
	Integer countByTypes(@Param("type") Integer type);
	
	@Query(COUNT_CATEGORY_PARENT_BY_ID)
	Integer countCategoryParentByID(@Param("id") Integer ID);
	
	@Query(SELECT_CATEGORY_BY_ID)
	Category SelectCategoryParentByID(@Param("id") Integer ID);
	
	@Query(value = SELECT_ALL_FILTER, nativeQuery=true)
	Page<Category> selectAllCategoryParentPage(@Param("name") String name,
											@Param("startDate") String startDate,@Param("endDate") String endDate,
											@Param("status") String status,@Param("type") Integer type, Pageable page );
	
	@Query(value = SELECT_ALL_FILTER_2, nativeQuery=true)
	Page<Category> selectAllCategoryParentchidentPage(@Param("name") String name,@Param("cateParentID") String cateParentID,
											@Param("startDate") String startDate,@Param("endDate") String endDate,
											@Param("status") String status,@Param("type") Integer type, Pageable page );
	
	@Query(value = test, nativeQuery=true)
	Page<Category> test(@Param("name") String name, Pageable page);
//	@Query(value = SELECT_ALL_FILTER, nativeQuery=true)
//	Page<Category> selectAllCategoryParentPage(@Param("name") String name,@Param("parentID") String parentID,
//											@Param("startDate") String startDate,@Param("endDate") String endDate,
//											@Param("status") Integer status,@Param("type") Integer type, Pageable page );
	
	@Query(value =SELECT_CATEGORY_BY_ID_AND_TYPE, nativeQuery=true)
	Category SELECT_CATEGORY_BY_ID_AND_TYPE(@Param("id") Integer ID, @Param("type") Integer type);
	
	@Query(value = "select create_date from category order by create_date asc limit 1", nativeQuery = true)
	String START_DATE();
	@Query(value = "select create_date from category order by create_date desc limit 1", nativeQuery = true)
	String END_DATE();
}
