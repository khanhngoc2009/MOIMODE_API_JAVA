package com.it15306.repository;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.Province;


@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer>  {
	final String SELECT_ALL = "SELECT p FROM Province p where p.status = 1";
	final String SELECT_BY_NAME = "SELECT p FROM Province p WHERE p.name =:province_name and p.status = 1 ";
	final String SELECT_BY_STATUS = "SELECT p FROM Province p WHERE p.status =:status";

	@Query(SELECT_ALL)
	List<Province> findAllProvince();

	@Query(SELECT_BY_NAME)
	Province findByName(@Param("province_name") String name);
	
	@Query(SELECT_BY_STATUS)
	List<Province> findByStatus(@Param("status") Integer status);

}
