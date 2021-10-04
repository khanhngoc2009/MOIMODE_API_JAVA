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

import com.it15306.entities.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {

	final String SELECT_ALL = "SELECT w FROM Ward w";
	final String SELECT_BY_NAME = "SELECT w FROM Ward w WHERE w.ward_name =:Ward_name";
	final String SELECT_BY_STATUS = "SELECT w FROM Ward w WHERE w.status =:status";
	final String SELECT_BY_DISTRICT = "SELECT w FROM Ward w WHERE w.district =:district";

	@Query(SELECT_ALL)
	List<Ward> findAllWard();

	@Query(SELECT_BY_NAME)
	Ward findByName(@Param("Ward_name") String name);

	@Query(SELECT_BY_STATUS)
	List<Ward> findByStatus(@Param("status") Integer status);

	@Query(SELECT_BY_DISTRICT)
	List<Ward> findByDistrict(@Param("district") String district);

}
