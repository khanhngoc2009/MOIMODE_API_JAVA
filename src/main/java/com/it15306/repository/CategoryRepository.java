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
	final String SELECT_BY_TYPE = "SELECT c FROM Category c WHERE c.type =:type";
	final String SELECT_BETWEEN_CREATE_DATE = "SELECT c FROM Category c WHERE c.create_date BETWEEN :ngay_bat_dau AND :ngay_ket_thuc";
	final String SELECT_BY_STATUS = "SELECT c FROM Category c WHERE c.status =:status";
	final String SELECT_ALL_CATEGORY_PARENT ="select C.category_id , C.category_name, C.type ,C.category_parent_id,C.description, C.url_image, C.status, C.create_date from category C where C.type ='1'";
	final String COUNT_BY_TYPE = "SELECT count(category_id) FROM Category c WHERE c.type =:type";
	final String COUNT_CATEGORY_PARENT_BY_ID = "SELECT count(category_id) FROM Category c WHERE c.type = 2 and c.category_parent_id =:id";
	@Query(SELECT_ALL)
	List<Category> findAllCategory();
	
	@Query(SELECT_BY_NAME)
	Category findByName(@Param("name") String category_name);
	
	@Query(SELECT_BY_PARENT_ID)
	Page<Category> findByCategoryParentId(@Param("category_parent_id") Integer category_parent_id,@Param("type") Integer type, Pageable page);
	
	@Query(SELECT_BY_TYPE)
	List<Category> findByType(@Param("type") Integer type);
	
	@Query(SELECT_BY_TYPE)
	Page<Category> findByTypePage(@Param("type") Integer type, Pageable page);
	
	@Query(SELECT_BETWEEN_CREATE_DATE)
	List<Category> findByCreateDate(@Param("ngay_bat_dau") Date ngay_bat_dau, @Param("ngay_ket_thuc") Date ngay_ket_thuc);
	
	@Query(SELECT_BY_STATUS)
	List<Category> findByStatus(@Param("status") Integer status);
	
	@Query(value = SELECT_ALL_CATEGORY_PARENT, nativeQuery=true)
	List<Category> selectAllCategoryParent();
	

	@Query(COUNT_BY_TYPE)
	Integer countByTypes(@Param("type") Integer type);
	
	@Query(COUNT_CATEGORY_PARENT_BY_ID)
	Integer countCategoryParentByID(@Param("id") Integer ID);
}
