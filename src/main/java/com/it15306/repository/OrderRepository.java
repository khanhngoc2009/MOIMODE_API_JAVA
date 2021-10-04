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

import com.it15306.entities.User;


@Repository
public interface OrderRepository extends JpaRepository<User, Integer>  {
	final String SELECT_ALL = "SELECT u FROM User u";
	final String SELECT_BY_EMAIL = "SELECT u FROM User u WHERE u.email =:email";
	final String SELECT_BY_ID = "SELECT u FROM User u WHERE u.id =:id";
	final String SELECT_BY_USERNAME = "SELECT u FROM User u WHERE u.username =:username";

	@Query(SELECT_ALL)
	List<User> findAllUser();
	
	@Query(SELECT_BY_EMAIL)
	User findByEmail(@Param("email") String email);
	
	@Query(SELECT_BY_USERNAME)
	User findByUsername2(String username);
	
	@Query(SELECT_BY_ID)
	User findById(@Param("id") String id);
	
	Optional<User> findByUsername(String userName);

}
