package com.it15306.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.District;
import com.it15306.entities.Province;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
	final String SELECT_ALL = "SELECT d FROM District d";
	final String SELECT_BY_NAME = "SELECT d FROM District d WHERE d.name =:district_name";
	final String SELECT_BY_STATUS = "SELECT d FROM District d WHERE d.status =:status";
	final String SELECT_BY_PROVINCE = "SELECT d FROM District d WHERE d.province =:province";

	@Query(SELECT_ALL)
	List<District> findAllDistrict();

	@Query(SELECT_BY_NAME)
	District findByName(@Param("district_name") String name);
	
	@Query(SELECT_BY_STATUS)
	List<District> findByStatus(@Param("status") Integer status);
	
	@Query(SELECT_BY_PROVINCE)
	List<District> findByProvince(@Param("province") Province province);

}
