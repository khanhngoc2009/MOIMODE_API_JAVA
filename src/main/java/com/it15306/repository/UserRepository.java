package com.it15306.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.Category;
import com.it15306.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {
	final String SELECT_ALL = "SELECT u FROM User u";
	final String SELECT_BY_EMAIL = "SELECT u FROM User u WHERE u.email =:email";
	final String SELECT_BY_ID = "SELECT u FROM User u WHERE u.id =:id";
	final String SELECT_BY_USERNAME = "SELECT u FROM User u WHERE u.username =:username";
	final String SELECT_FILTER_USER="select * from user where user_id > 0 and  username like %?1% and email like %?2% and create_date between ?3 and ?4 and activated like %?5% ORDER BY create_date desc";
	
	final String SELECT_CHECK_USER = "select * from user where  user_id > 0 and username = ?1 or email= ?2 or phone = ?3";
	
	@Query(value = "select create_date from user order by create_date asc limit 1", nativeQuery = true)
	String START_DATE();
	@Query(value = "select create_date from user order by create_date desc limit 1", nativeQuery = true)
	String END_DATE();
	final String thongKeCountUser = "SELECT COUNT(u.id) FROM User u where  u.id > 0 and u.activated= ?1";


	@Query(SELECT_ALL)
	List<User> findAllUser();
	
	@Query(SELECT_BY_EMAIL)
	User findByEmail(@Param("email") String email);
	
	@Query(SELECT_BY_USERNAME)
	User findByUsername2(String username);
	
	@Query(SELECT_BY_ID)
	User findById(@Param("id") String id);
	
	Optional<User> findByUsername(String userName);
	
	@Query(value = SELECT_FILTER_USER, nativeQuery=true)
	Page<User> selectFillterUser(@Param("username") String username,@Param("email") String email,@Param("create_date") String create_date,
			@Param("end_date") String end_date,@Param("status") String status,Pageable page);
	@Query(thongKeCountUser)
	Integer thongKeCountUser(@Param("activated") Integer activated);
	
	
	@Query(value = SELECT_CHECK_USER, nativeQuery=true)
	List<User> SELECT_CHECK_USER(@Param("username") String username,@Param("email") String email,@Param("phone") String create_date);
}
