package com.it15306.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>  {
	final String SELECT_ALL = "SELECT c FROM Category c";
	final String SELECT_BY_NAME = "SELECT c FROM Category c WHERE c.category_name =:category_name";
	final String SELECT_BY_PARENT_ID = "SELECT c FROM Category c WHERE c.category_parent_id =:category_parent_id";
	final String SELECT_BY_TYPE = "SELECT c FROM Category c WHERE c.type =:type";
	final String SELECT_BETWEEN_CREATE_DATE = "SELECT c FROM Category c WHERE c.create_date BETWEEN :ngay_bat_dau AND :ngay_ket_thuc";
	final String SELECT_BY_STATUS = "SELECT c FROM Category c WHERE c.status =:status";

	@Query(SELECT_ALL)
	List<Category> findAllCategory();
	
	@Query(SELECT_BY_NAME)
	Category findByName(@Param("category_name") String category_name);
	
	@Query(SELECT_BY_PARENT_ID)
	List<Category> findByCategoryParentId(@Param("category_parent_id") Integer category_parent_id);
	
	@Query(SELECT_BY_TYPE)
	List<Category> findByType(@Param("type") Integer type);
	
	@Query(SELECT_BETWEEN_CREATE_DATE)
	List<Category> findByIdCreateDate(@Param("ngay_bat_dau") Date ngay_bat_dau, @Param("ngay_ket_thuc") Date ngay_ket_thuc);
	
	@Query(SELECT_BY_STATUS)
	List<Category> findByStatus(@Param("status") Integer status);
}
