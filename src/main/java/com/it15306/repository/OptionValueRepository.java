package com.it15306.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.Options;
import com.it15306.entities.OptionValue;
import com.it15306.entities.User;


@Repository
public interface OptionValueRepository extends JpaRepository<OptionValue, Integer>  {
	final String SELECT_BY_OPTION = "SELECT ov FROM OptionValue ov WHERE ov.option=:option";
	
	final String DELETE_BY_OPTIONS = "DELETE FROM OptionValue ov where ov.option=:option";
	
	
	@Query(SELECT_BY_OPTION)
	List<OptionValue> findAllOptionValue(@Param("option") Options option);
	
	@Query(DELETE_BY_OPTIONS)
	String deleteByOption(@Param("option") Options option);
}
